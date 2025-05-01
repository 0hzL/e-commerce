package com.sparta.ecommerce.global.email.controller;

import com.sparta.ecommerce.domain.user.repository.UserRepository;
import com.sparta.ecommerce.domain.user.service.UserService;
import com.sparta.ecommerce.global.email.service.EmailService;
import com.sparta.ecommerce.global.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final RedisService redisService;
    private final UserService userService;
    private final UserRepository userRepository;

    /* 인증번호 발송 */
    @GetMapping("/sendverificationcode")
    public ResponseEntity<String> sendVerificationCode(@RequestParam String email) {
        if(userRepository.existsByEmail(email)){
            return ResponseEntity.badRequest().body("이미 가입된 이메일입니다.");
        }
        String verificationCode = emailService.sendVerificationCode(email);
        return ResponseEntity.ok("인증번호 발송 완료 !" + verificationCode); //verificationcode는 확인용
    }
    

    /* 인증번호 대조 */
    @PostMapping("/checkverificationcode")
    public boolean checkVerificationCode(@RequestParam String verificationcode, @RequestParam String email) {
        if (verificationcode == null || email == null || verificationcode.length() != 6) {
            return false;
        }
        String storedCode = redisService.getVerificationCode(email);
        if(storedCode != null && storedCode.equals(verificationcode)) {
            redisService.setVerificationStatus(email, true);
            return true;
        }
        return false;
    }

}
