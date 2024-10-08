package com.sparta.ecommerce.domain.user.entity;

import com.sparta.ecommerce.domain.user.dto.SignUpRequestDto;
import com.sparta.ecommerce.domain.wishlist.entity.Wishlist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String password;
    private String phone;
    private String address;
    private String email;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    private boolean is_email_verified;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wishlist> wishlist = new ArrayList<>();

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
