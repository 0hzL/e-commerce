package com.sparta.ecommerce.domain.stock.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {

    private long itemId;
    private int stockQuantity;
}
