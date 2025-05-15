package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="PickupPoint")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PickupPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PickupPointId")
    private Integer pickupPointId;

    @Column(name="Contact", nullable = false, length = 12)
    private String contact;
}
