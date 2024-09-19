package com.sparta.ecommerce.domain.wishlistitem.repository;

import com.sparta.ecommerce.domain.wishlistitem.entity.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {

}
