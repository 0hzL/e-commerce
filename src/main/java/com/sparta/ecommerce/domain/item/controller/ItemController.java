package com.sparta.ecommerce.domain.item.controller;

import com.sparta.ecommerce.domain.item.dto.ItemRegisterDto;
import com.sparta.ecommerce.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;


    /* 아이템 등록 */
    @PostMapping("")
    public ResponseEntity<String> registerItem(@RequestBody ItemRegisterDto itemRegisterDto){
        itemService.registerItem(itemRegisterDto);
        return ResponseEntity.ok("아이템 등록 완료 !");
    }




}
