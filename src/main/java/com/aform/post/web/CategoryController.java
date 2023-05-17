package com.aform.post.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aform.post.domain.category.Category;
import com.aform.post.service.CategoryService;


@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category.getCategoryType()));
        
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @DeleteMapping("/delete/{categoryPk}")
    public ResponseEntity<Long> deleteCategory(@RequestParam(value="categoryPk") Long categoryPk){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryPk));
    }
}
