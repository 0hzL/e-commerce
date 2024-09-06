package com.sparta.ecommerce.domain.iteminfo.repository;

import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemInfoRepository extends JpaRepository<ItemInfo, Integer> {
}
