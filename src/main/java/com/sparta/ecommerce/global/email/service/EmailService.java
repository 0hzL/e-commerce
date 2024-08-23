package com.sparta.ecommerce.global.email.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public String sendVerificationCode(String email) {
        String verificationCode = randomVerficationCode();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(from);
        message.setSubject("💻인증 번호💻");
        message.setText("👀인증번호 코드 : " + verificationCode);

        javaMailSender.send(message);

        return verificationCode;
    }

    private String randomVerficationCode() {
        Random random = new Random();
        int code =random.nextInt(90000) + 100000; //100000~999999
        return String.valueOf(code);
    }
}
