package com.sparta.ecommerce.global.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    /* 인증번호 저장 (유효 시간 3분) */
    public void setVerificationCode(String email,String verificationCode){
        redisTemplate.opsForValue().set(email,verificationCode,3, TimeUnit.MINUTES);
    }


    /* 저장된 인증번호 확인 */
    public String getVerificationCode(String email){
        String verificationCode = redisTemplate.opsForValue().get(email);
        if(verificationCode == null){
            throw new RuntimeException("다시 인증해주세요");
        }
        return verificationCode;
    }

}
