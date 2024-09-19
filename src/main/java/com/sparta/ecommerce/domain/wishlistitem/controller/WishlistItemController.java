package com.sparta.ecommerce.domain.wishlistitem.controller;

import com.sparta.ecommerce.domain.wishlistitem.service.WishlistItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist_item")
public class WishlistItemController {

    private final WishlistItemService wishlistItemService;


}
