package com.sparta.ecommerce.domain.item.entity;

import com.sparta.ecommerce.domain.iteminfo.entity.ItemInfo;
import com.sparta.ecommerce.domain.order.entity.Order;
import com.sparta.ecommerce.domain.stock.entity.Stock;
import com.sparta.ecommerce.domain.wishlist.entity.Wishlist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "item")
@EntityListeners(AuditingEntityListener.class)
public class Item {
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String itemName;
    private int price;


    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<>();

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private Wishlist wishlist;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private Stock stock;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private ItemInfo itemInfo;


}
