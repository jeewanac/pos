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

import com.example.pos.entity.CategoryEntity;
import com.example.pos.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryEntity>> getAllCategories(){
        List<CategoryEntity> categories = categoryService.getAllCategories();

        if(categories!=null){
            return ResponseEntity.status(200).body(categories);
        }else{
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryEntity> findCategoryById(Long id){
        CategoryEntity categoryEntity = categoryService.findById(id);

        if(categoryEntity!=null){
            return ResponseEntity.status(200).body(categoryEntity);
        }else{
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity categoryEntity){
        try {
            return ResponseEntity.status(200).body(categoryService.createCategory(categoryEntity));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable Long id, @RequestBody CategoryEntity categoryEntity){
        return ResponseEntity.status(200).body(categoryService.updateCategory(id, categoryEntity));
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
