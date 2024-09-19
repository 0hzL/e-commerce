package com.sparta.ecommerce.domain.wishlistitem.entity;

import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.wishlist.entity.Wishlist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "wishlist_item")
public class WishlistItem {

    @Id
    @Column(name="wishlist_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private int quantity;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime addedAt;
}
