package com.example.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.ItemEntity;
import com.example.pos.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
    @Query("SELECT s FROM StockEntity s JOIN s.items i WHERE i = :itemEntity")
    List<StockEntity> getStockByItem(@Param("itemEntity") ItemEntity itemEntity);
}
