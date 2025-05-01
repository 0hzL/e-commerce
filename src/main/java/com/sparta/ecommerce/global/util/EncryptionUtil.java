package com.sparta.ecommerce.global.util;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

    private final AES256TextEncryptor encryptor;

    public EncryptionUtil(@Value("${encryption.secret}") String secretKey) {
        encryptor = new AES256TextEncryptor();
        encryptor.setPassword(secretKey);
    }

    public String encrypt(String plainText) {
        return encryptor.encrypt(plainText);
    }

    public String decrypt(String cipherText) {
        return encryptor.decrypt(cipherText);
    }
}
