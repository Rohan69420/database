package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="Location")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LocationId")
    private Integer locationId;

    @Column(name = "Country", length = 20)
    private String country;

    @Column(name = "City", length = 30)
    private String city;

    @Column(name = "Street", length = 30)
    private String street;

    @Column(name = "Zip", length = 10)
    private String zip;

}