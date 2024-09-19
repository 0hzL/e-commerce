package com.sparta.ecommerce.domain.wishlist.repository;

import com.sparta.ecommerce.domain.wishlist.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface WishlistRepository extends JpaRepository<Wishlist,Long> {
    Optional<Wishlist> findByUserId(long userId);
}
