package com.example.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pos.entity.CategoryEntity;

@Service
public interface CategoryService {
    List<CategoryEntity> getAllCategories();
    CategoryEntity findById(Long id);
    CategoryEntity createCategory(CategoryEntity categoryEntity);
    CategoryEntity updateCategory(Long id, CategoryEntity categoryEntity);
    void deleteCategory(Long id);

}
