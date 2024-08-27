package com.sparta.ecommerce.domain.user.controller;

import com.sparta.ecommerce.domain.user.dto.SignUpRequestDto;
import com.sparta.ecommerce.domain.user.entity.User;
import com.sparta.ecommerce.domain.user.repository.UserRepository;
import com.sparta.ecommerce.domain.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @PostMapping("/signup")
    private ResponseEntity post(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = new User(signUpRequestDto);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser.getId());
    }

    @GetMapping
    private ResponseEntity get(@RequestParam long id){
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(user);
    }
}
