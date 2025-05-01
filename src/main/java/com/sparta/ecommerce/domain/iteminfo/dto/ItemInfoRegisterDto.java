package com.sparta.ecommerce.domain.iteminfo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ItemInfoRegisterDto {
    private long ItemId;
    private String information;
    private int unitPrice;
}
