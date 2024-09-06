package com.sparta.ecommerce.domain.iteminfo.controller;

import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import com.sparta.ecommerce.domain.iteminfo.service.ItemInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
