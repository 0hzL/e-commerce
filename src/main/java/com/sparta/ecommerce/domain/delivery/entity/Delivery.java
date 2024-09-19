package com.sparta.ecommerce.domain.delivery.entity;

import com.sparta.ecommerce.domain.order.entity.Order;
import com.sparta.ecommerce.domain.order.entity.Status;
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
@Table(name = "delivery")
@EntityListeners(AuditingEntityListener.class)
public class Delivery {
    @Id
    @Column(name="delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deliveriedAt;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order orders;

}
