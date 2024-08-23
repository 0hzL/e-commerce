package com.sparta.ecommerce.global.email.controller;

import com.sparta.ecommerce.global.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping("/sendverificationcode")
    public String sendVerificationCode(@RequestParam String email) {
        String verificationCode = emailService.sendVerificationCode(email);
        return verificationCode;
    }
}
