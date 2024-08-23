package com.sparta.ecommerce.domain.user.repository;

import com.sparta.ecommerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
