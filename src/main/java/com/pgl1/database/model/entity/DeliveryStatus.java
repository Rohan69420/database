package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "DeliveryStatus")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="DeliveryStatusId")
    private Integer statusId;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;
}
