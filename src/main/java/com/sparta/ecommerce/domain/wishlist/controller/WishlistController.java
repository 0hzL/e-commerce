package com.sparta.ecommerce.domain.wishlist.controller;

import com.sparta.ecommerce.domain.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    /* 재고가 있는 경우 위시리스트 추가 가능 */

    /* 마이페이지 - 1 위시리스트에 넣은 상품들 확인 */
    /* 마이페이지 - 2 위시리스트에서 선택한 제품의 상세 정보 */
    /* 마이페이지 - 3 위시리스트에 넣은 상품 수량 변경 및 주문 ? (주문도 여기서 해야할까나 ?) */
    /* 마이페이지 - 4 위시리스트에 넣은 상품 수정(삭제 및 수량 변경?) */

}
