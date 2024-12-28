package org.team10.washcode.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.team10.washcode.Enum.UserRole;
import org.team10.washcode.entity.redis.Token;
import org.team10.washcode.repository.TokenRepository;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    // 토큰(Access,Refresh) 만료시간(ms)
    @Value("${ACCESS_TOKEN_EXPIRATION_TIME}")
    private long ACCESS_EXPIRATION_TIME; // 10분

    @Value("${REFRESH_TOKEN_EXPIRATION_TIME}")
    private long REFRESH_EXPIRATION_TIME; // 1일

    private final SecretKey SECRETKEY;
    private final TokenRepository tokenRepository;

    // 인증키 랜덤값 생성
    @Autowired
    public JwtProvider(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
        this.SECRETKEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Access 토큰 생성
    public String generateAccessToken(int id, UserRole role){
        Claims claims = Jwts.claims().setSubject(String.valueOf(id));
        claims.put("role",role);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+ACCESS_EXPIRATION_TIME))
                .signWith(SECRETKEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh 토큰 생성
    public String generateRefreshToken(int id, UserRole role){
        Claims claims = Jwts.claims().setSubject(String.valueOf(id));
        claims.put("role",role);
        Date now = new Date();

        // RefreshToken(RT) 생성
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+REFRESH_EXPIRATION_TIME))
                .signWith(SECRETKEY, SignatureAlgorithm.HS256)
                .compact();

        try {
            // RT 캡슐화
            Token token = new Token(id, refreshToken, REFRESH_EXPIRATION_TIME);

            // RT를 Redis에 저장
            tokenRepository.save(token);

            return refreshToken;
        } catch (Exception e) {
            System.out.println("[Redis] RefreshToken save failed: " + e.getMessage());
            return null;
        }
    }

    // JWT 검증(변조 및 만료여부 확인)
    public boolean validateToken(String token){
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(SECRETKEY)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    // 헤더에서 Access 토큰 가져오기
    public String resolveAccessToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 헤더에서 Refresh 토큰 가져오기
    public String resolveRefreshToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Refresh");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰에서 id 반환
    public int getId(String token){
        return Integer.parseInt(Jwts.parserBuilder()
                .setSigningKey(SECRETKEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    // 토큰에서 role 반환
    public UserRole getRole(String token){
        return UserRole.valueOf(Jwts.parserBuilder()
                .setSigningKey(SECRETKEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class));
    }
}
