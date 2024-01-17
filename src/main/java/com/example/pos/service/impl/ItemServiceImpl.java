package com.example.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.dto.ItemDTO;
import com.example.pos.entity.CategoryEntity;
import com.example.pos.entity.ItemEntity;
import com.example.pos.entity.StockEntity;
import com.example.pos.repository.CategoryRepository;
import com.example.pos.repository.ItemRepository;
import com.example.pos.repository.StockRepository;
import com.example.pos.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired 
    private StockRepository stockRepository;

    @Override
    public List<ItemEntity> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public ItemEntity findById(Long id) {
         return itemRepository.findById(id).orElse(null);
    }

    @Override
    public ItemEntity createItem(ItemDTO itemDTO) {
        CategoryEntity existCategory = categoryRepository.findById(itemDTO.getCategoryId()).orElse(null);
        StockEntity existStock = stockRepository.findById(itemDTO.getStockId()).orElse(null);

        if(existCategory!=null && existStock!=null){
            ItemEntity itemEntity = new ItemEntity();

            itemEntity.setName(itemDTO.getName());
            itemEntity.setPrice(itemDTO.getPrice());
            itemEntity.setCategoryEntity(existCategory);
            itemEntity.setStockEntity(existStock);

            return itemRepository.save(itemEntity);
        }else{
            return null;
        }

         
    }

    @Override
    public ItemEntity updateItem(Long id, ItemEntity itemEntity) {
        ItemEntity exist = itemRepository.findById(id).orElse(null);

        if (exist!=null) {
            exist.setName(itemEntity.getName());
            exist.setPrice(itemEntity.getPrice());
            exist.setCategoryEntity(itemEntity.getCategoryEntity());
            exist.setStockEntity(itemEntity.getStockEntity());
            

            return itemRepository.save(exist);
        }else{
            return null;
        }
    }

    @Override
    public List<ItemEntity> findItemsByCategory(Long id) {
        CategoryEntity exist = categoryRepository.findById(id).orElse(null);

        if(exist!=null){
            return itemRepository.findItemsByCategory(exist);
        }else{
            return null;
        }
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
    
}
