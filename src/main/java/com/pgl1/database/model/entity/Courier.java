package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="Courier")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourierId")
    private Integer courierId;
}
