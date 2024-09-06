package com.sparta.ecommerce.global.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // PasswordEncoder bean 정의
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/user/signup","/sendverificationcode","/item/**","/iteminfo/**").permitAll() // 누구나 접근 가능
                        .anyRequest().authenticated()
                );
//                .formLogin((form) -> form
//                        .loginPage("/login") // 커스텀 로그인 페이지
//                        .permitAll()
//                )
//                .logout((logout) -> logout.permitAll()); // 로그아웃도 누구나 접근 가능

        return http.build();
    }
}
