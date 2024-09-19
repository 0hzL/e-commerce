package com.sparta.ecommerce.domain.wishlist.service;

import com.sparta.ecommerce.domain.user.repository.UserRepository;
import com.sparta.ecommerce.domain.wishlist.entity.Wishlist;
import com.sparta.ecommerce.domain.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;

    /* user의 위시리스트 생성 */
    public Wishlist createWishlist(long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId).orElse(null);
        if(wishlist == null){
            wishlist = new Wishlist();
            wishlist.setUser(userRepository.findById(userId).orElse(null));
            wishlistRepository.save(wishlist);
        }
        return wishlist;
    }

}
