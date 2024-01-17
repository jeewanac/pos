package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.dto.CheckoutDTO;
import com.example.pos.entity.CheckoutEntity;

@Service
public interface CheckoutService {
    List<CheckoutEntity> findAll();
    CheckoutEntity findById(Long id);
    CheckoutEntity createCheckout(CheckoutDTO checkoutDTO);
    void deleteCheckout(Long id);

}
