package com.sparta.ecommerce.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    private String userName;
    private String password;
    private String phone;
    private String address;
    private String email;


    public SignUpRequestDto(String userName, String password, String phone, String address, String email) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
}
