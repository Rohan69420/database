package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="Item")
@Data
@Builder
@EqualsAndHashCode(exclude = "id")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="name", nullable = false, length = 20)
    private String name;

    @Column(name="description", length = 200)
    private String description;

    @Column(name="weight", nullable = false)
    private float weight;
}
