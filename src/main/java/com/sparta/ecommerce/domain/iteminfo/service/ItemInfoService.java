package com.sparta.ecommerce.domain.iteminfo.service;

import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.item.repository.ItemRepository;
import com.sparta.ecommerce.domain.iteminfo.dto.UserItemInfoDto;
import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import com.sparta.ecommerce.domain.iteminfo.repository.ItemInfoRepository;
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

    /* 위시리스트에서 선택한 제품의 상세 정보 */
    public UserItemInfoDto getSelectItemInfo(long itemId){
        Item item = itemRepository.findById(itemId).get();
        UserItemInfoDto userItemInfoDto = new UserItemInfoDto();
        userItemInfoDto.setItemId(item.getId());
        userItemInfoDto.setItemName(item.getItemName());
        userItemInfoDto.setUnitPrice(item.getPrice());
        userItemInfoDto.setInformation(item.getItemInfo().getInformation());

        return userItemInfoDto;
    }
}
