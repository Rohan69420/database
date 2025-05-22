package com.pgl1.database.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Builder;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name="items")
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

    @Column(name="created_timestamp")
    @CreationTimestamp
    private LocalDateTime createdTimestamp;
}
