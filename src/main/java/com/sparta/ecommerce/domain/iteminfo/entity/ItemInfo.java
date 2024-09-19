package com.sparta.ecommerce.domain.iteminfo.entity;

import com.sparta.ecommerce.domain.item.entity.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "iteminfo")
@EntityListeners(AuditingEntityListener.class)
public class ItemInfo {
    @Id
    @Column(name="info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String information;
    private int unitPrice;

    @OneToOne
    @JoinColumn(name = "item_id")
//    @JsonIgnore
    private Item item;
}
