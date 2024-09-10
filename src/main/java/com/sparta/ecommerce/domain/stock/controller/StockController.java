package com.sparta.ecommerce.domain.stock.controller;

import com.sparta.ecommerce.domain.stock.dto.StockDto;
import com.sparta.ecommerce.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {
    /* 재고 업데이트, 재고 등록 */
    private final StockService stockService;

    @PostMapping("")
    public ResponseEntity<String> registerStock(@RequestBody StockDto stockDto) {
        stockService.registerStock(stockDto);
        return ResponseEntity.ok("재고 등록 완료");
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<String> UpdateStock(@PathVariable int itemId, @RequestParam int stockQuantity) {
        stockService.updateStock(itemId,stockQuantity);
        return ResponseEntity.ok("재고 업데이트 완료");
    }
}
