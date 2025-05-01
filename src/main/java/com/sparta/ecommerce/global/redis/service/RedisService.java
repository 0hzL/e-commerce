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
        redisTemplate.opsForValue().set("email:"+ email,verificationCode,3, TimeUnit.MINUTES);
    }

    /* 저장된 인증번호 확인 */
    public String getVerificationCode(String email){
        String key = "email:" + email;
        String verificationCode = redisTemplate.opsForValue().get(key);
        if(verificationCode == null){
            throw new RuntimeException("다시 인증해주세요");
        }
        return verificationCode;
    }

    /* 인증 완료 t/f 저장 */
    public void setVerificationStatus(String email, boolean status) {
        redisTemplate.opsForValue().set("verified:" + email, String.valueOf(status), 10, TimeUnit.MINUTES);
    }

    /* username 중복확인으로 쓰이고 나선 삭제  */
    public void deleteVerificationCode(String username){
        redisTemplate.delete("valid:username:" + username);
    }

    /* username 중복확인 여부 저장 */
    public void checkUserNameIsDuplicated(String username, boolean status) {
        redisTemplate.opsForValue().set("valid:username:" + username, String.valueOf(status), 10, TimeUnit.MINUTES);
    }

    /* Email 중복확인 여부 저장 */
    public void checkEmailIsDuplicated(String email, boolean status) {
        redisTemplate.opsForValue().set("valid:email:" + email, String.valueOf(status), 10, TimeUnit.MINUTES);
    }

    /* 로그인 및 로그아웃 */

}
