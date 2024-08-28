package com.sparta.ecommerce.global.email.controller;

import com.sparta.ecommerce.global.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    /* 인증번호 발송 */
    @GetMapping("/sendverificationcode")
    public String sendVerificationCode(@RequestParam String email) {
        String verificationCode = emailService.sendVerificationCode(email);
        return verificationCode;
    }
}
