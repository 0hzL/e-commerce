package com.sparta.ecommerce.domain.item.dto;

import com.sparta.ecommerce.domain.iteminfo.dto.ItemInfoRegisterDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRegisterDto {
    private int itemId;
    private String itemName;
    private int price;
    private ItemInfoRegisterDto iteminfo;
}
