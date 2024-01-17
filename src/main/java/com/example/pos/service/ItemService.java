package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.dto.ItemDTO;
import com.example.pos.entity.ItemEntity;

@Service
public interface ItemService {
    List<ItemEntity> getAllItems();
    ItemEntity findById(Long id);
    ItemEntity createItem(ItemDTO itemDTO);
    ItemEntity updateItem(Long id, ItemEntity itemEntity);
    List<ItemEntity> findItemsByCategory(Long id);
    void deleteItemById(Long id);

}
