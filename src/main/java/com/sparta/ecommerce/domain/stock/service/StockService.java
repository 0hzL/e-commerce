package com.sparta.ecommerce.domain.stock.service;

import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.item.repository.ItemRepository;
import com.sparta.ecommerce.domain.stock.dto.StockDto;
import com.sparta.ecommerce.domain.stock.entity.Stock;
import com.sparta.ecommerce.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ItemRepository itemRepository;

    /* 재고 등록 */
    public void registerStock(StockDto stockdto) {
        Item item = itemRepository.findById(stockdto.getItemId()).orElseThrow();
        Stock stock = new Stock();
        stock.setItem(item);
        stock.setStockQuantity(stockdto.getStockQuantity());
        stockRepository.save(stock);
    }

    /* 재고 업데이트 */
    public void updateStock(long itemId, int stockQuantity) {
       Stock stock = stockRepository.findById(itemId).orElseThrow();
       stock.setStockQuantity(stockQuantity);
       stockRepository.save(stock);
    }
}
