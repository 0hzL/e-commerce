package com.sparta.ecommerce.domain.order.entity;

import lombok.Getter;

@Getter
public enum Status {
    // 주문완료/배송중/배송완료/주문취소/환불완료
    ORDERED("주문완료"),
    SHIPPING("배송중"),
    DELIVERED("배송완료"),
    CANCELED("주문취소"),
    REFUNDED("환불완료");

    private final String message;

    Status(String message) {
        this.message = message;
    }
}
