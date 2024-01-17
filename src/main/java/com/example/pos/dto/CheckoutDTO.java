package com.example.pos.dto;

import java.util.List;

import lombok.Data;

@Data
public class CheckoutDTO {
    private List<Long> items;
}
