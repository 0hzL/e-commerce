package com.sparta.ecommerce.domain.item.service;

import com.sparta.ecommerce.domain.item.dto.ItemRegisterDto;
import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.item.repository.ItemRepository;
import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import com.sparta.ecommerce.domain.iteminfo.repository.ItemInfoRepository;
import com.sparta.ecommerce.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemInfoRepository itemInfoRepository;
    private final StockRepository stockRepository;

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

    /* Item Id로 상품 찾기 (재고 없을 때 노출 x ) */
    public Item getItemById(long itemId) {
        if (isItemVisible(itemId)) {
            return itemRepository.findById(itemId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 ID의 아이템이 없습니다."));
        } else {
            throw new IllegalArgumentException("해당 아이템은 노출 불가능합니다.");
        }
    }

    /* 아이템 리스트(재고 없을때 노출 x) */
    public List<Item> getVisibleItems() {
        List<Item> allItems = itemRepository.findAll();
        return allItems.stream()
                .filter(item -> isItemVisible(item.getId()))
                .collect(Collectors.toList());
    }



    /* 공통 메서드 - 아이템 재고 확인 후 노출 여부 */
    public boolean isItemVisible(long itemId) {
        if (itemRepository.existsById(itemId) && stockRepository.findStockQuantityByItemId(itemId) > 0) {
            return true;
        }
        return false;
    }

}
