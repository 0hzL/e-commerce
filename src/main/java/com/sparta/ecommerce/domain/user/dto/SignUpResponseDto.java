package com.sparta.ecommerce.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private long id;
    private String userName;
    private String phone;
    private String address;
    private String email;


    public SignUpResponseDto(long id, String email, String userName, String phone, String address) {
        this.id=id;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
}