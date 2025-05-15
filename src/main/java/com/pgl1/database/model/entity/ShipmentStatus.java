package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ShipmentStatus")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ShipmentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ShipmentStatusId")
    private Integer shipmentStatusId;

    // probably should be using a separate class object for this
    @Column(name="TimeStamp")
    private long timeStamp;

    @Column(name="ItemDescription", nullable = true)
    private String itemDescription;
}
