package com.sparta.ecommerce.domain.auth;

import com.sparta.ecommerce.domain.user.dto.LogInRequestDto;
import com.sparta.ecommerce.domain.user.dto.LogInResponseDto;
import com.sparta.ecommerce.domain.user.entity.User;
import com.sparta.ecommerce.domain.user.repository.UserRepository;
import com.sparta.ecommerce.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String reissueAccessToken(String refreshToken,String accessToken) {
        // 1. 토큰 유효성 검사
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }
        if (!jwtUtil.isAccessTokenExpired(accessToken)) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }

        // 2. 토큰에서 이메일 추출
        String email = jwtUtil.getEmailFromToken(refreshToken);

        // 3. Redis에서 저장된 리프레시 토큰 확인
        String storedRefreshToken = redisTemplate.opsForValue().get("refresh_token:" + email);
        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new IllegalArgumentException("리프레시 토큰이 일치하지 않거나 만료되었습니다.");
        }

        // 4. 새로운 액세스 토큰 생성
        return jwtUtil.createAccessToken(email);
    }

    /* 로그인 */
    public LogInResponseDto login(LogInRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        String accessToken = jwtUtil.createAccessToken(user.getEmail());
        String refreshToken = jwtUtil.createRefreshToken(user.getEmail());
        //일단 실험을 위해 10분으로 설정
        redisTemplate.opsForValue().set("refresh_token:" + user.getEmail(), refreshToken, 10, TimeUnit.MINUTES);

        return new LogInResponseDto(accessToken, refreshToken);
    }

    /* 로그아웃 */
    public void logout(String accessToken) {
        // 1. 토큰 유효성 검사
        if (!jwtUtil.validateToken(accessToken)) {
            throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
        }
        // 2. 이메일 추출 (getEmailFromToken은 만료된 토큰에서도 Claims 추출 가능해야 함)
        String email = jwtUtil.getEmailFromToken(accessToken);
        // 3. 만료 시간 계산
        Date expiration = jwtUtil.getClaims(accessToken).getExpiration();
        long now = System.currentTimeMillis();
        long remaining = expiration.getTime() - now;
        // 4. 블랙리스트 등록
        redisTemplate.opsForValue().set("blacklist:" + accessToken, "logout", remaining, TimeUnit.MILLISECONDS);
        // 5. 리프레시 토큰 제거
        redisTemplate.delete("refresh_token:" + email);

    }
}

