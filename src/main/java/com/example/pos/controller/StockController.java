package com.example.pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.entity.StockEntity;
import com.example.pos.service.StockService;

@RestController
@CrossOrigin(origins = "*")
public class StockController {
    @Autowired
    private StockService stockService;

    @GetMapping("/stocks")
    public ResponseEntity<List<StockEntity>> getAllStocks() {
        return ResponseEntity.status(200).body(stockService.getAllStock());
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<StockEntity> findById(@PathVariable Long id){
        return ResponseEntity.status(200).body(stockService.findById(id));   
    }

    @PostMapping("/stocks")
    public ResponseEntity<StockEntity> createStock(@RequestBody StockEntity stockEntity){
        return ResponseEntity.status(200).body(stockService.createStock(stockEntity));
    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<StockEntity> updateEntity(@PathVariable Long id, @RequestBody StockEntity stockEntity){
        return ResponseEntity.status(200).body(stockService.updataStock(id, stockEntity));
    }

    @DeleteMapping("/stocks/{id}")
    public void deleteStock(@PathVariable Long id){
        stockService.deleteById(id);
    }
}
