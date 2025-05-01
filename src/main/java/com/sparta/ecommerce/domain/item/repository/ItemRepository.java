package com.sparta.ecommerce.domain.item.repository;

import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.iteminfo.dto.UserItemInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

//    @Query("SELECT new com.sparta.ecommerce.domain.iteminfo.dto.UserItemInfoDto(i.id, i.itemName, i.price, ii.information) " +
//            "FROM Item i LEFT JOIN FETCH i.itemInfo ii WHERE i.id = :itemId")
//    Optional<UserItemInfoDto> findItemInfoById(@Param("itemId") long itemId);

    @Query("SELECT new com.sparta.ecommerce.domain.iteminfo.dto.UserItemInfoDto(i.id, i.itemName, i.price, ii.information) " +
            "FROM Item i JOIN i.itemInfo ii WHERE i.id = :id")
    Optional<UserItemInfoDto> findItemInfoById(@Param("id") long id);




}
