package com.sparta.ecommerce.domain.iteminfo.service;

import com.sparta.ecommerce.domain.item.repository.ItemRepository;
import com.sparta.ecommerce.domain.iteminfo.dto.UserItemInfoDto;
import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import com.sparta.ecommerce.domain.iteminfo.repository.ItemInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemInfoService {

    private final ItemInfoRepository itemInfoRepository;
    private final ItemRepository itemRepository;

    public ItemInfo getItemInfo(long itemId) {
        return itemInfoRepository.findById(itemId).orElse(null);
    }

    public UserItemInfoDto getSelectItemInfo(long itemId) {
        return itemRepository.findItemInfoById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemId));
    }
}
