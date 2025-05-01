package com.sparta.ecommerce.domain.user.controller;

import com.sparta.ecommerce.domain.auth.AuthService;
import com.sparta.ecommerce.domain.user.dto.SignUpRequestDto;
import com.sparta.ecommerce.domain.user.dto.SignUpResponseDto;
import com.sparta.ecommerce.domain.user.repository.UserRepository;
import com.sparta.ecommerce.domain.user.service.UserService;
import com.sparta.ecommerce.global.jwt.JwtUtil;
import com.sparta.ecommerce.global.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final RedisTemplate redisTemplate;
    private final UserService userService;
    private final RedisService redisService;
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();
        String userName = signUpRequestDto.getUserName();

        boolean emailVerified = "true".equals(redisTemplate.opsForValue().get("verified:" + email));
        boolean emailValid = "true".equals(redisTemplate.opsForValue().get("valid:email:" + email));
        boolean usernameValid = "true".equals(redisTemplate.opsForValue().get("valid:username:" + userName));

        if (!emailValid || !usernameValid) { //중복 // 통합답변만들기
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복 확인이 완료되지 않았습니다.");
        }

        if (!emailVerified) { //이메일 인증 // 통합답변만들기
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이메일 인증이 완료되지 않았습니다.");
        }

        SignUpResponseDto responseDto = userService.signUp(signUpRequestDto);

        return ResponseEntity.ok(responseDto);
    }





    /* 프론트에서 ui에 바로바로 중복여부가 뜨려면 따로 중복 확인을 해줘야함  */
    //이메일 중복 확인
    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Object>> checkEmail(@RequestParam String email) {
        boolean isDuplicated = userRepository.existsByEmail(email);

        Map<String, Object> response = new HashMap<>();
        response.put("available", !isDuplicated);
        response.put("message", isDuplicated ? "이미 존재하는 이메일입니다." : "사용 가능한 이메일입니다.");
        // 통합답변만들기 이부분도 ?
        // ✅ Redis에 인증 정보 저장
        if (!isDuplicated) {
            redisService.checkEmailIsDuplicated(email,true);
        }

        return ResponseEntity.ok(response);
    }


    // 닉네임(이름) 중복 확인
    @GetMapping("/check-username")
    public ResponseEntity<Map<String, Object>> checkUserName(@RequestParam String username) {
        boolean isDuplicated = userRepository.existsByUserName(username);
        System.out.println(isDuplicated);
        Map<String, Object> response = new HashMap<>();
        response.put("available", !isDuplicated);
        response.put("message", isDuplicated ? "이미 존재하는 닉네임입니다." : "사용 가능한 닉네임입니다.");

        // ✅ Redis에 인증 정보 저장
        if (!isDuplicated) {
           redisService.checkUserNameIsDuplicated(username,true);
        }

        return ResponseEntity.ok(response);
    }



}
