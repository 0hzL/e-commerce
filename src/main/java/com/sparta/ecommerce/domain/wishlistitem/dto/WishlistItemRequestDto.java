package com.sparta.ecommerce.domain.wishlistitem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistItemRequestDto {

    private int wishlist_id;
    private int item_id;
    private int quantity;

}
