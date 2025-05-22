package com.pgl1.database.model.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, max = 12, message="Your name must be between 4 to 12")
    @Column(name="name", length = 20, nullable = false)
    private String name;

    @NotBlank(message = "You're trying to write an empty phone number")
    @Pattern(regexp = "^\\+?[0-9\\s\\-()]{7,20}$", message = "Invalid phone number format")
    @Column(name="phone", length = 10, nullable = false)
    private String phone;

    @NotBlank(message = "You cannot have a blank email")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

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
