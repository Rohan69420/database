package com.pgl1.database.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Data
@Builder
@EqualsAndHashCode(exclude = "id")

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", length = 20, nullable = false)
    private String name;

    @NotNull
    @Column(name="phone", length = 10, nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name="location_id")
    private Location location;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdTimestamp;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTimestamp;


}
