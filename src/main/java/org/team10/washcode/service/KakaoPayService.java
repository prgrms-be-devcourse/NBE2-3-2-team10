package org.team10.washcode.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.team10.washcode.RequestDTO.order.KakaoPayReqDTO;
import org.team10.washcode.ResponseDTO.order.KakaoPayApproveRes;
import org.team10.washcode.entity.Payment;
import org.team10.washcode.entity.redis.KakaoPayPgToken;
import org.team10.washcode.entity.redis.KakaoPaymentInfo;
import org.team10.washcode.jwt.JwtProvider;
import org.team10.washcode.repository.db.PaymentRepository;
import org.team10.washcode.repository.redis.KakaoPayPgTokenRepository;
import org.team10.washcode.repository.redis.KakaoPaymentInfoRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoPayService {

//    @Value("${kakaopay.key.client-id}")
    private String cid = "TC0ONETIME";

    @Value("${kakaopay.key.client-secret}")
    private String clientSecret;

    @Value("${kakaopay.key.secret}")
    private String secret;

    @Value("${kakaopay.key.secret-dev}")
    private String secretDev;

    private final KakaoPaymentInfoRepository kakaopaymentInfoRepository;
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final KakaoPayPgTokenRepository kakaoPayPgTokenRepository;

    @Getter @Setter
    public static class ReadyKakakoPayRes {
        @JsonProperty("tid")
        public String tid;

        @JsonProperty("next_redirect_pc_url")
        public String next_redirect_pc_url;

        @JsonProperty("next_redirect_mobile_url")
        public String next_redirect_mobile_url;
    }

    public ResponseEntity<?> payReady(int userId , KakaoPayReqDTO kakaoPayReqDTO) {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "SECRET_KEY " + secretDev);

        // HTTP Body 생성
        Map<String, Object> body = new HashMap<>();
        body.put("cid", cid);
        body.put("cid_secret", clientSecret);
        body.put("partner_order_id", String.valueOf(kakaoPayReqDTO.getPaymentId())); // String으로 변환
        body.put("partner_user_id", String.valueOf(userId)); // String으로 변환
        body.put("item_name", kakaoPayReqDTO.getName());
        body.put("quantity", kakaoPayReqDTO.getQuantity()); // 숫자 값 그대로 사용
        body.put("total_amount", kakaoPayReqDTO.getTotalPrice()); // 숫자 값 그대로 사용
        body.put("tax_free_amount", 0); // 숫자 값 그대로 사용
        body.put("approval_url", "http://localhost:8080/order/completed");
        body.put("cancel_url", "http://localhost:8080/order/cancel");
        body.put("fail_url", "http://localhost:8080/order/fail");

        // HTTP 요청 (https://open-api.kakaopay.com/online/v1/payment/ready 으로)
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        RestTemplate template = new RestTemplate();
        ResponseEntity<ReadyKakakoPayRes> res = null;
        ReadyKakakoPayRes readyKakakoPayRes = null;

        try {
            String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
            res = template.postForEntity(url, requestEntity, ReadyKakakoPayRes.class);
            readyKakakoPayRes = res.getBody(); // Body 값을 DTO로 변환
        } catch (HttpClientErrorException e) {
            System.out.println("[kakao Login HTTP API 오류] " + e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body("카카오페이 오류 발생");
        }

        // 객체를 JSON 문자열로 변환
        try {
            String readyKakakoPayResJson = new ObjectMapper().writeValueAsString(readyKakakoPayRes);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("ReadyKakaoPayRes", readyKakakoPayResJson);

            // Redis Key & Value 생성
            String redisKey = cid + ":" + userId;
            KakaoPaymentInfo paymentInfo = new KakaoPaymentInfo(redisKey, kakaoPayReqDTO.getPaymentId(), readyKakakoPayRes.getTid());

            // Redis에 데이터 저장
            kakaopaymentInfoRepository.save(paymentInfo);

            // ResponseEntity에 헤더 포함하여 응답
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body("결제 준비 완료");
        } catch (JsonProcessingException e) {
            System.out.println("[Json 파싱 오류] Kakaopay Ready 파싱 과정에서 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body("카카오페이 오류 발생");
        }
    }

    @Transactional
    public ResponseEntity<?> payCompleted(String token , Model model) {
        System.out.println(token);
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "SECRET_KEY " + secretDev);

        // RT로 새로운 AccessToken 발급
        int userId = 1;

        // Redis에서 tid, partner_order_id 가져오기
        KakaoPaymentInfo payInfo = kakaopaymentInfoRepository.findById(cid + ":" + userId)
                .orElseThrow(() -> new RuntimeException("Redis에서 KakaoPaymentInfo를 찾을 수 없습니다: " + cid + ":" + userId));
        String tid = payInfo.getTid();
        Integer partnerOrderId = payInfo.getPartnerOrderId();
        kakaopaymentInfoRepository.deleteById(cid + ":" + userId);  // Redis에서 삭제

        // HTTP Body 생성
        Map<String, String> body = new HashMap<>();
        body.put("cid", cid);                                         // 가맹점 코드(테스트용)
        body.put("tid", tid);                                         // 결제 고유번호
        body.put("partner_order_id", String.valueOf(partnerOrderId)); // 주문번호
        body.put("partner_user_id", String.valueOf(userId));          // 회원 아이디
        body.put("pg_token", token);                                  // 결제승인 요청을 인증하는 토큰

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, headers);

        // KakaoPay API 호출
        RestTemplate template = new RestTemplate();
        String url = "https://open-api.kakaopay.com/online/v1/payment/approve";
        KakaoPayApproveRes kakaoPayApproveRes = null;
        try {
            kakaoPayApproveRes = template.postForObject(url, requestEntity, KakaoPayApproveRes.class);
        } catch (RestClientException e) {
            return ResponseEntity
                    .badRequest()
                    .body("카카오페이 결제 승인 요청에 실패했습니다: " + e.getMessage());
        }

        // Redis에 pgToken 저장
        try {
            KakaoPayPgToken pgToken = new KakaoPayPgToken("pgToken:" + token, partnerOrderId);
            System.out.println("pgToken:" + token);
            System.out.println(pgToken.getPartnerOrderId());
            kakaoPayPgTokenRepository.save(pgToken);
        } catch (Exception e) {
            System.out.println("Redis에 pgToken 저장 중 오류 발생: " + e.getMessage());
        }

        // Payment 엔티티에 KakaoPayApproveRes 정보 업데이트
        Payment payment = paymentRepository.findById(partnerOrderId)
                .orElseThrow(() -> new RuntimeException("해당 ID로 Payment를 찾을 수 없습니다: " + partnerOrderId));
        payment.setKakaoPayData(kakaoPayApproveRes);

        // model에 담기
        model.addAttribute("aid", kakaoPayApproveRes.getAid());
        model.addAttribute("approvedAt", kakaoPayApproveRes.getApprovedAt());

        return new ResponseEntity<>("/order/completed", HttpStatus.OK);
    }
}
