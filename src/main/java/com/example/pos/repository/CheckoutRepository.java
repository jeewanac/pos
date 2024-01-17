package com.example.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.CheckoutEntity;

@Repository  
public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Long> {
    
}
