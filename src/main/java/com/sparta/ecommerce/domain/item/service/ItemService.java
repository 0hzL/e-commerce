package com.sparta.ecommerce.domain.item.service;

import com.sparta.ecommerce.domain.item.dto.ItemRegisterDto;
import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.item.repository.ItemRepository;
import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import com.sparta.ecommerce.domain.iteminfo.repository.ItemInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemInfoRepository itemInfoRepository;

    /* 등록되어 있는 상품 리스트 보기 */
    public List<Item> getItemListAll(){
        return itemRepository.findAll();
    }

    /* 아이템 등록 */
    public void registerItem(ItemRegisterDto itemRegisterDto){
        Item item = new Item();
        item.setItemName(itemRegisterDto.getItemName());
        item.setPrice(itemRegisterDto.getPrice());

        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setInformation(itemRegisterDto.getIteminfo().getInformation());
        itemInfo.setUnitPrice(itemRegisterDto.getIteminfo().getUnitPrice());
        itemInfo.setItem(item);

        item.setItemInfo(itemInfo);

        itemRepository.save(item);
        itemInfoRepository.save(itemInfo);

    }

    /* Item Id로 상품 찾기 */
    public Item getItemById(int itemId){
        return itemRepository.findById(itemId).get();
    }
}
