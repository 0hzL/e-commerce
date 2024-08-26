package com.sparta.ecommerce.domain.user.entity;

import com.sparta.ecommerce.domain.user.dto.SignUpRequestDto;
import com.sparta.ecommerce.domain.wishlist.entity.Wishlist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "users")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;
    private String password;
    private String phone;
    private String address;
    private String email;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private boolean is_email_verified;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wishlist wishlist;

    public User(String email, String password, String username, String phone, String address) {
        this.email = email;
        this.password = password;
        this.userName = username;
        this.phone = phone;
        this.address = address;
    }

    public User(SignUpRequestDto signUpRequestDto) {
        this.userName = signUpRequestDto.getUserName();
        this.password = signUpRequestDto.getPassword();
        this.phone = signUpRequestDto.getPhone();
        this.address = signUpRequestDto.getAddress();
        this.email = signUpRequestDto.getEmail();
    }
}
