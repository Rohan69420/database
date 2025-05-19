package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="Item")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ItemId")
    private Integer itemId;

    @Column(name="ItemName", nullable = false, length = 30)
    private String itemName;

    @Column(name="ItemDescription", nullable = true, length = 200)
    private String itemDescription;

    @Column(name="ItemWeight", nullable = false)
    private float itemWeight;

    @ManyToOne
    @JoinColumn(name="orderId", nullable = false)
    private Order order;
}
