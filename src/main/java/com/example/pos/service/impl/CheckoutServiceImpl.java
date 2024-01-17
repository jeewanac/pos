package com.example.pos.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.dto.CheckoutDTO;
import com.example.pos.entity.CheckoutEntity;
import com.example.pos.entity.ItemEntity;
import com.example.pos.entity.StockEntity;
import com.example.pos.repository.CheckoutRepository;
import com.example.pos.repository.ItemRepository;
import com.example.pos.repository.StockRepository;
import com.example.pos.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<CheckoutEntity> findAll() {
        return checkoutRepository.findAll();
    }

    @Override
    public CheckoutEntity findById(Long id) {
        return checkoutRepository.findById(id).orElse(null);
    }

    @Override
    public CheckoutEntity createCheckout(CheckoutDTO checkoutDTO) {
        List<Long> itemIds = checkoutDTO.getItems();
        Set<ItemEntity> items = new HashSet<>();
        Double total = 0.0;

        for (Long itemId : itemIds) {
            ItemEntity item = itemRepository.findById(itemId).orElse(null);
            if (item != null) {
                items.add(item);
                total += item.getPrice();
                StockEntity stock = item.getStockEntity();
                if (stock != null) {
                    int currentQty = stock.getQty();
                    if (currentQty > 0) {
                        currentQty -= 1;
                        stock.setQty(currentQty);
                        stockRepository.save(stock);
                    } else {
                        throw new RuntimeException("Outof Stock : " + item.getName());
                    }
                }
            }
        }
        LocalDateTime orderTime = LocalDateTime.now();
        CheckoutEntity checkoutEntity = new CheckoutEntity();
        checkoutEntity.setTotal(total);
        checkoutEntity.setOrderTime(orderTime);

        Set<ItemEntity> uniqueItems = checkoutEntity.getItems();
        uniqueItems.addAll(items);
        checkoutEntity.setItems(uniqueItems);

        return checkoutRepository.save(checkoutEntity);

    }

    @Override
    public void deleteCheckout(Long id) {
        checkoutRepository.deleteById(id);
    }

}
