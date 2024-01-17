package com.example.pos.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CheckoutEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;
    private LocalDateTime orderTime;

    @ManyToMany
    @JoinTable(
            name = "checkout_item",
            joinColumns = @JoinColumn(name = "checkout_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<ItemEntity> items = new HashSet<>();
}
