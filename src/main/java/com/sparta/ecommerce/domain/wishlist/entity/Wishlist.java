package com.sparta.ecommerce.domain.wishlist.entity;

import com.sparta.ecommerce.domain.item.entity.Item;
import com.sparta.ecommerce.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "wishlist")
@EntityListeners(AuditingEntityListener.class)
public class Wishlist {

    @Id
    @Column(name="wish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
