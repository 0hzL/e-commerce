package com.sparta.ecommerce.domain.user.service;

import com.sparta.ecommerce.domain.user.dto.SignUpRequestDto;
import com.sparta.ecommerce.domain.user.dto.SignUpResponseDto;
import com.sparta.ecommerce.domain.user.entity.User;
import com.sparta.ecommerce.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RedisTemplate redisTemplate;
    private final PasswordEncoder passwordEncoder;


    //회원가입
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {
        //이메일 본인인증해야함
        String email = requestDto.getEmail();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String username = requestDto.getUserName();
        String phone = requestDto.getPhone();
        String address = requestDto.getAddress();

        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Optional<User> checkUserName = userRepository.findByUserName(username);
        if (checkUserName.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이름입니다.");
        }


        Optional<User> checkPhone = userRepository.findByPhone(phone);
        if (checkPhone.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 번호입니다.");
        }


        User user = new User(email, password, username,phone,address);
        User savedUser = userRepository.save(user);
        return new SignUpResponseDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getUserName(),
                savedUser.getPhone(),
                savedUser.getAddress());
    }

    //로그인
}
