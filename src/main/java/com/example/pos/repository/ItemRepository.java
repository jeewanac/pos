package com.example.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pos.entity.CategoryEntity;
import com.example.pos.entity.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
   @Query("SELECT i FROM ItemEntity i WHERE i.categoryEntity = : categoryEntity")
   List<ItemEntity> findItemsByCategory(@Param("categoryEntity") CategoryEntity categoryEntity);
}
