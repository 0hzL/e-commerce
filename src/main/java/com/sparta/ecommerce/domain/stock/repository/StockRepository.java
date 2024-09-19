package com.sparta.ecommerce.domain.stock.repository;

import com.sparta.ecommerce.domain.stock.entity.Stock;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Long> {
    /* 스톡 재고 여부
    * s.item.id = :itemId = stock 테이블에 연결된 item 테이블의 id는 itemId */
    @Query("SELECT s.stockQuantity FROM Stock s WHERE s.item.id = :itemId")
    int findStockQuantityByItemId(@Param("itemId") long itemId);

}
