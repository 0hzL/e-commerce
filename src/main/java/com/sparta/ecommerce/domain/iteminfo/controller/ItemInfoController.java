package com.sparta.ecommerce.domain.iteminfo.controller;

import com.sparta.ecommerce.domain.iteminfo.dto.UserItemInfoDto;
import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import com.sparta.ecommerce.domain.iteminfo.service.ItemInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/iteminfo")
public class ItemInfoController {

    private final ItemInfoService itemInfoService;

    /* Item Id로 상품 찾기 */
    @GetMapping("")
    public ItemInfo getItemById(@RequestParam int itemId){
        return itemInfoService.getItemInfo(itemId);
    }

    /* 위시리스트에서 선택한 제품의 상세 정보 */
    @GetMapping("/{itemId}")
    public UserItemInfoDto getSelectItemInfo(@PathVariable int itemId){
        return itemInfoService.getSelectItemInfo(itemId);
    }
}
