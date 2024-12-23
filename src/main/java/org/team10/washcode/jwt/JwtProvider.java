package org.team10.washcode.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.team10.washcode.Enum.UserRole;


import javax.crypto.SecretKey;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    private final SecretKey SECRETKEY;

    // 토큰(Access,Refresh) 만료시간(ms)
    private final long ACCESS_EXPIRATION_TIME = 600000; // 10분
    private final long REFRESH_EXPIRATION_TIME = 86400000; // 1일

    public JwtProvider() {
        this.SECRETKEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // Access 토큰 생성
    public String generateAccessToken(int id, UserRole role){
        Claims claims = Jwts.claims().setSubject(String.valueOf(id));
        claims.put("role",role);
        Date now = new Date();
        Date validity = new Date(now.getTime()+ACCESS_EXPIRATION_TIME);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SECRETKEY, SignatureAlgorithm.HS256)
                .compact();
    }

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

    // JWT 검증
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRETKEY)
                    .build()
                    .parseClaimsJwt(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰에서 Email 추출
    public String getId(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRETKEY)
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }

    // 헤더에서 토큰 가져오기
    public String getToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
