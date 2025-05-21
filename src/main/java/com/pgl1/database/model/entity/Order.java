package com.pgl1.database.model.entity;

import com.pgl1.database.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Orders")
@Data
@Builder
@EqualsAndHashCode(exclude = "id")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "order")
    @JoinColumn(name = "items")
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToOne
    @JoinColumn(name="source_pickup_id")
    private PickupPoint source;

    @OneToOne
    @JoinColumn(name="destination_pickup_id")
    private PickupPoint destination;

    @Column(name = "type")
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private OrderStatus status;

    @Column(name = "date")
    @CreationTimestamp
    private LocalDateTime createdTimestamp;
}
