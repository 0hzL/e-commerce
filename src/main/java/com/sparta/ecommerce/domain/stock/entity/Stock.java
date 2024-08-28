package com.sparta.ecommerce.domain.stock.entity;

import com.sparta.ecommerce.domain.item.entity.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "stock")
public class Stock {
    @Id
    @Column(name="stock_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int stockQuantity;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
