package com.sparta.ecommerce.domain.item.controller;

import com.sparta.ecommerce.domain.item.dto.ItemRegisterDto;
import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    /* 등록되어 있는 상품 리스트 보기 */
    @GetMapping("")
    public List<Item> getItemListAll(){
        return itemService.getItemListAll();
    }

    /* 아이템 등록 */
    @PostMapping("")
    public ResponseEntity<String> registerItem(@RequestBody ItemRegisterDto itemRegisterDto){
        itemService.registerItem(itemRegisterDto);
        return ResponseEntity.ok("아이템 등록 완료 !");
    }

}
