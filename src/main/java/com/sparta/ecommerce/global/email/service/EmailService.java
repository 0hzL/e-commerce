package com.sparta.ecommerce.global.email.service;

import com.sparta.ecommerce.domain.user.service.UserService;
import com.sparta.ecommerce.global.redis.service.RedisService;
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
    private final UserService userService;
    private final RedisService redisService;

    @Value("${spring.mail.username}")
    private String from;

    /* 이메일 코드 전송 */
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

    /* 이메일 인증 업데이트 */
    public boolean checkVerificationCode(String email, String verificationCode) {
        String redisCode = redisService.getVerificationCode(email);
        if (redisCode!=null && redisCode.equals(verificationCode)) {
            //유저서비스에서 업데이트(트루)
            userService.updateEmailIsVerfied(email,true);
            redisService.deleteVerficationCode(email);
            return true;
        } else{
            false;
        }
    }

    /* 랜덤 인증번호 생성 */
    private String randomVerficationCode() {
        Random random = new Random();
        int code =random.nextInt(90000) + 100000; //100000~999999
        return String.valueOf(code);
    }


}
