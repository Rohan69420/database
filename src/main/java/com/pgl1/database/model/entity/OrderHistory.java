package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="OrderHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderHistoryId")
    private Integer orderHistoryId;

    @Column(name= "createdTime")
    private long createdTime;
}
