package com.example.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.entity.ItemEntity;
import com.example.pos.entity.StockEntity;
import com.example.pos.repository.ItemRepository;
import com.example.pos.repository.StockRepository;
import com.example.pos.service.StockService;

@Service
public class StockServiceImpl implements StockService{
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<StockEntity> getAllStock() {
        return stockRepository.findAll();
    }

    @Override
    public StockEntity findById(Long id) {
         return stockRepository.findById(id).orElse(null);
    }

    @Override
    public StockEntity createStock(StockEntity stockEntity) {
         return stockRepository.save(stockEntity);
    }

    @Override
    public StockEntity updataStock(Long id, StockEntity stockEntity) {
         StockEntity exist = stockRepository.findById(id).orElse(null);

         if(exist!=null){
            exist.setName(stockEntity.getName());
            exist.setQty(stockEntity.getQty());
            exist.setItems(stockEntity.getItems());

            return stockRepository.save(exist);
         }else{
            return null;
         }
    }

    @Override
    public void deleteById(Long id) {
         stockRepository.deleteById(id);
    }

    @Override
    public List<StockEntity> getStockByItem(Long id) {
        ItemEntity exist = itemRepository.findById(id).orElse(null);

        if(exist!=null){
            return stockRepository.getStockByItem(exist);
        }else{
            return null;
        }
    }
    
}
