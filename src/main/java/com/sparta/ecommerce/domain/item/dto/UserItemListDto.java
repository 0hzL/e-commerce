package com.sparta.ecommerce.domain.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserItemListDto {
        private long ItemId;
        private String ItemName;
        private int unitPrice;
        private int quantity;
        private int totalPrice;
    }
