package com.example.pos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pos.entity.CategoryEntity;
import com.example.pos.repository.CategoryRepository;
import com.example.pos.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity findById(Long id) {
         return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public CategoryEntity createCategory(CategoryEntity categoryEntity) {
         return categoryRepository.save(categoryEntity);
    }

    @Override
    public CategoryEntity updateCategory(Long id, CategoryEntity categoryEntity) {
         CategoryEntity exist = categoryRepository.findById(id).orElse(null);

         if(exist!=null){
            exist.setName(categoryEntity.getName());
            return categoryRepository.save(exist);
         }else{
            return null;
         }
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
    
}
