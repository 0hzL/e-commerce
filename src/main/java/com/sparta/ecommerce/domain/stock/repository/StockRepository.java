package com.sparta.ecommerce.domain.stock.repository;

import com.sparta.ecommerce.domain.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
