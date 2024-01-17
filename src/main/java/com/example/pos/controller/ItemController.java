package com.example.pos.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.pos.dto.ItemDTO;
import com.example.pos.entity.CategoryEntity;
import com.example.pos.entity.ItemEntity;
import com.example.pos.service.CategoryService;
import com.example.pos.service.ItemService;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/items")
    public ResponseEntity<List<ItemEntity>> gelAllItems(){
        List<ItemEntity> itemEntities = itemService.getAllItems();
        if(itemEntities!=null){
            return ResponseEntity.status(200).body(itemEntities);
        }else{
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemEntity> findById(@PathVariable Long id){
        ItemEntity item = itemService.findById(id);
        if(item!=null){
            return ResponseEntity.status(200).body(item);
        }else{
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<ItemEntity> updateItem(@PathVariable Long id, @RequestBody ItemEntity itemEntity){
        return ResponseEntity.status(200).body(itemService.updateItem(id, itemEntity));
    }

    @PostMapping("/items")
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemDTO itemDTO){
        try {
            return ResponseEntity.status(200).body(itemService.createItem(itemDTO));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/categories/{id}/items")
    public ResponseEntity<List<ItemEntity>> getItemsByCategory(@PathVariable Long id){
        CategoryEntity categoryEntity = categoryService.findById(id);

        if(categoryEntity!=null){
            return ResponseEntity.status(200).body(itemService.findItemsByCategory(id));
        }else{
            return ResponseEntity.status(400).body(null);
        }
    }
}
