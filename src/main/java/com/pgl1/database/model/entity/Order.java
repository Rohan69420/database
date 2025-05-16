package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer orderId;

    @Column(name = "CustomerID")
    private Integer customerId;

    @Column(name = "OrderDate", length = 20)
    private String orderDate;
}
