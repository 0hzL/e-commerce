package com.sparta.ecommerce.domain.wishlistitem.service;

import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.item.repository.ItemRepository;
import com.sparta.ecommerce.domain.wishlist.entity.Wishlist;
import com.sparta.ecommerce.domain.wishlist.repository.WishlistRepository;
import com.sparta.ecommerce.domain.wishlistitem.entity.WishlistItem;
import com.sparta.ecommerce.domain.wishlistitem.repository.WishlistItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistItemService {
    private final WishlistItemRepository wishlistItemRepository;
    private final ItemRepository itemRepository;
    private final WishlistRepository wishlistRepository;

    /* 위시리스트에 아이템 추가 api
    * 위시리스트가 없을때는 생성 후 아이템 추가
    * 위시리스트에 동일한 아이템이 있을 경우 -> 수량 증가
    * 그 외엔 위시리스트에 해당 아이템 추가
    * ( 단, 아이템의 재고가 있을 시 = 아이템이 노출되어 있을 시 ) */
    public WishlistItem createWishlistItem(long userId, Item item, int quantity) {

        Wishlist wishlist = getWishlistByUserId(userId);
        //추우에 아이템 노출 api부분에서 재고가 없을때 노출 x 조건 걸어야함
        Optional<WishlistItem> existingItem = wishlistItemByItemId(item.getId(),wishlist);

        if (existingItem.isPresent()) {
            WishlistItem wishlistItem = existingItem.get();
            wishlistItem.setQuantity(wishlistItem.getQuantity() + quantity); // 수량 증가 처리

            return wishlistItemRepository.save(wishlistItem);
        } else {
            WishlistItem wishlistItem = new WishlistItem();
            wishlistItem.setWishlist(wishlist);
            wishlistItem.setItem(item);
            wishlistItem.setQuantity(quantity);
            wishlist.getWishlistItems().add(wishlistItem);

            return wishlistItemRepository.save(wishlistItem);
        }
    }

    /* user의 위시리스트 아이템 리스트 */
    public List<WishlistItem> getWishlistItemsByUserId(long userId) {
        Wishlist wishlist = getWishlistByUserId(userId);
        return new ArrayList<>(wishlist.getWishlistItems());
    }


    /* 위시리스트에 넣은 상품 수량 변경 */
    public WishlistItem getWishlistItemByItemId(long itemId, long userId, int newQuantity) {
            Wishlist wishlist = getWishlistByUserId(userId);
            Optional<WishlistItem> existingItem = wishlistItemByItemId(itemId,wishlist);
            WishlistItem wishlistItem = existingItem.get();
            if(newQuantity > 0 ){
                wishlistItem.setQuantity(newQuantity);
            }else {
                throw new IllegalArgumentException("수량은 0보다 커야 합니다.");
            }
            return wishlistItemRepository.save(wishlistItem);
    }

    /* 위시리스트에 넣은 상품 삭제 */
    public void deleteWishlistItem(long itemId, long userId) {
        Wishlist wishlist = getWishlistByUserId(userId);
        Optional<WishlistItem> existingItem = wishlistItemByItemId(itemId, wishlist);
        WishlistItem wishlistItem = existingItem.get();
        wishlist.getWishlistItems().remove(wishlistItem);
        wishlistItemRepository.delete(wishlistItem); //db 삭제
    }


    /* 공통 메서드 - 위시리스트 존재 여부 */
    private Wishlist getWishlistByUserId(long userId) {
        return wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("위시리스트가 존재하지 않습니다. 사용자 ID: " + userId));
    }

    /* 공통 메서드 - 위시리스트 내 상품 존재 여부 */
    private Optional<WishlistItem> wishlistItemByItemId(long itemId,Wishlist wishlist) {
        return wishlist.getWishlistItems().stream()
                .filter(wishlistItem -> wishlistItem.getItem().getId() == itemId)
                .findFirst();
    }
}


