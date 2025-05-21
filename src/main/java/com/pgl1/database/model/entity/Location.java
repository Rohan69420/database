package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="locations")
@Data
@Builder
@EqualsAndHashCode(exclude = "id")

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country", length = 20)
    private String country;

    @Column(name = "city", length = 20)
    private String city;

    @Column(name = "street", length = 20)
    private String street;

}