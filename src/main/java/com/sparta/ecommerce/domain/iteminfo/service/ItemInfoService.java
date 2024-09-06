package com.sparta.ecommerce.domain.iteminfo.service;

import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import com.sparta.ecommerce.domain.iteminfo.repository.ItemInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemInfoService {

    private final ItemInfoRepository itemInfoRepository;

    public ItemInfo getItemInfo(int itemId) {
        return itemInfoRepository.findById(itemId).orElse(null);
    }
}
