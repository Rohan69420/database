package com.pgl1.database.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="pickup_point")
@Data
@Builder
@EqualsAndHashCode(exclude = "id")

public class PickupPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name", nullable = false, length = 20)
    private String name;

    @Column(name="contact", nullable = false, length = 10)
    private String contact;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "description", length = 100)
    private String description;
}
