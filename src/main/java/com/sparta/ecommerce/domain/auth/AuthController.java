package com.sparta.ecommerce.domain.auth;

import com.sparta.ecommerce.domain.user.dto.LogInRequestDto;
import com.sparta.ecommerce.domain.user.dto.LogInResponseDto;
import com.sparta.ecommerce.global.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    // 리프레시 토큰을 통해 액세스 토큰 재발급
    @PostMapping("/reissue")
    public ResponseEntity<?> reissueAccessToken(@CookieValue("refresh_token") String refreshToken, @RequestHeader("Authorization") String authorizationHeader) {

        try {
            String accessToken = jwtUtil.resolveToken(authorizationHeader);
            // 리프레시 토큰을 통해 새로운 액세스 토큰 발급
            String newAccessToken = authService.reissueAccessToken(refreshToken,accessToken);
            System.out.println("새로 발급한 토큰: " + newAccessToken);
            // 새 액세스 토큰을 HTTP 응답의 Authorization 헤더로 설정
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + newAccessToken)
                    .body("새로운 액세스 토큰이 발급되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("리프레시 토큰이 유효하지 않습니다.");
        }
    

    }


    @PostMapping("/login")
    public ResponseEntity<LogInResponseDto> login(@RequestBody LogInRequestDto requestDto,
                                                  HttpServletResponse response) {
        LogInResponseDto tokens = authService.login(requestDto);
        // Refresh Token을 HttpOnly 쿠키로 설정
        Cookie refreshTokenCookie = new Cookie("refresh_token", tokens.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true); // HTTPS일 때만 동작 (개발 단계에서는 false로도 테스트 가능)
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(60 * 60 * 24); // 1일 (초 단위)

        response.addCookie(refreshTokenCookie);

        // Access Token만 바디에 담아 보냄
        return ResponseEntity.ok(new LogInResponseDto(tokens.getAccessToken(), null));
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token= jwtUtil.resolveToken(request);
        authService.logout(token);
        return ResponseEntity.ok("로그아웃 성공");
    }

}

