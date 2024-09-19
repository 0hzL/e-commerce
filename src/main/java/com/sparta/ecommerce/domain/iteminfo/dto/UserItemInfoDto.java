package com.sparta.ecommerce.domain.iteminfo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserItemInfoDto {
    private long ItemId;
    private String ItemName;
    private String information;
    private int unitPrice;

}
