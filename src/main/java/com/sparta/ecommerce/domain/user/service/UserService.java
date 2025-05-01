package com.sparta.ecommerce.domain.user.service;

import com.sparta.ecommerce.domain.user.dto.SignUpRequestDto;
import com.sparta.ecommerce.domain.user.dto.SignUpResponseDto;
import com.sparta.ecommerce.domain.user.entity.User;
import com.sparta.ecommerce.domain.user.repository.UserRepository;
import com.sparta.ecommerce.global.jwt.JwtUtil;
import com.sparta.ecommerce.global.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisTemplate redisTemplate;
    private final PasswordEncoder passwordEncoder;
    private final EncryptionUtil encryptionUtil;
    private final JwtUtil jwtUtil;


    /* 회원가입 */
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        // 암호화 제거
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String username = requestDto.getUserName();
        String phone = requestDto.getPhone();
        String address = requestDto.getAddress();

        User user = new User(email, password, username, phone, address);
        User savedUser = userRepository.save(user);

        return SignUpResponseDto.builder()
                .id(savedUser.getId())
                .phone(savedUser.getPhone())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .address(savedUser.getAddress())
                .build();
    }

    /* 인증번호 업데이트 */
    public void updateEmailIsVerified(String email, boolean isVerified) {
        boolean isDuplicated = userRepository.existsByEmail(email);
    }

}
