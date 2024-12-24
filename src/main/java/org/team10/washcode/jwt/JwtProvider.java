package org.team10.washcode.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;
import org.team10.washcode.Enum.UserRole;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    private final SecretKey SECRETKEY;

    // 토큰(Access,Refresh) 만료시간(ms)
    private final long ACCESS_EXPIRATION_TIME = 600000; // 10분
    private final long REFRESH_EXPIRATION_TIME = 86400000; // 1일

    // 인증키 랜덤값 생성
    public JwtProvider() {
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

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+REFRESH_EXPIRATION_TIME))
                .signWith(SECRETKEY, SignatureAlgorithm.HS256)
                .compact();
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

    // 헤더에 Access 토큰 넣기
    public void setHeaderAccessToken(HttpServletResponse response, String token){
        response.setHeader("Authorization", "bearer "+ token);
    }

    // 헤더에 Refresh 토큰 넣기
    public void setHeaderRefreshToken(HttpServletResponse response, String token){
        response.setHeader("RefreshToken", "bearer "+token);
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
                .get("role",String.class));
    }
}
