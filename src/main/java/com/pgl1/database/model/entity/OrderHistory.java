package com.pgl1.database.model.entity;

import com.pgl1.database.enums.ChangeType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="OrderHistory")
@Data
@Builder
@EqualsAndHashCode(exclude = "id")

public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name="previous_status" ,nullable = true)
    private String previousStatus;

    @Column(name="new_status" ,nullable = false)
    private String newStatus;

    @Enumerated(EnumType.STRING)
    @Column(name="change_type", nullable = false)
    private ChangeType changeType;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}