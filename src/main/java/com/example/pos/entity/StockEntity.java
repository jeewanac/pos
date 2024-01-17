package com.example.pos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer qty;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stockEntity", cascade = CascadeType.ALL)
    private List<ItemEntity> items;
}
