package com.example.pos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "stockId")
    private StockEntity stockEntity;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private Set<CheckoutEntity> checkouts = new HashSet<>();
}
