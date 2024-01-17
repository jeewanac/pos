package com.example.pos.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private String name;
    private Double price;
    private Long categoryId;
    private Long stockId;
}
