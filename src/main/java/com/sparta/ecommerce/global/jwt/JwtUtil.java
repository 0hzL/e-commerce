package com.sparta.ecommerce.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private Key key;
//    private final long ACCESS_TOKEN_TIME = 1000 * 60 * 60; // 1시간
//    private final long REFRESH_TOKEN_TIME = 1000L * 60 * 60 * 24 * 1; // 1일

    //확인용
    private final long ACCESS_TOKEN_TIME = 1000 * 60 * 1; //1분
    private final long REFRESH_TOKEN_TIME = 1000L * 60 * 5; // 5분

    @PostConstruct
    public void init() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(String email) {
        return createToken(email, ACCESS_TOKEN_TIME);
    }

    public String createRefreshToken(String email) {
        return createToken(email, REFRESH_TOKEN_TIME);
    }

    private String createToken(String email, long expireTime) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expireTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            // 만료된 토큰의 경우, 로그를 남기고 false 반환
            System.out.println("Expired JWT token: " + token);
            return false;
        } catch (SecurityException | MalformedJwtException e) {
            // JWT 구문 오류 발생 시 처리
            System.out.println("Invalid JWT token: " + token);
            return false;
        } catch (UnsupportedJwtException e) {
            // JWT 형식이 지원되지 않는 경우 처리
            System.out.println("Unsupported JWT token: " + token);
            return false;
        } catch (IllegalArgumentException e) {
            // 토큰이 비어있는 경우 처리
            System.out.println("JWT claims string is empty.");
            return false;
        }
    }

    public boolean isAccessTokenExpired(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return false; //토큰 유효
        } catch (ExpiredJwtException e) {
            return true; //토큰 만료
        }
    }

//
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }

    //오버로딩해서 두가지 버전 모두 쓰기
    public String resolveToken(HttpServletRequest request) {
        return resolveToken(request.getHeader("Authorization"));
    }

    public String resolveToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // 만료된 토큰에서도 claims 추출 가능
            return e.getClaims();
        }
    }
}
