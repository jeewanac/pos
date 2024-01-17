package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.entity.StockEntity;

@Service
public interface StockService {
    List<StockEntity> getAllStock();
    StockEntity findById(Long id);
    StockEntity createStock(StockEntity stockEntity);
    StockEntity updataStock(Long id, StockEntity stockEntity);
    void deleteById(Long id);
    List<StockEntity> getStockByItem(Long id);

}
