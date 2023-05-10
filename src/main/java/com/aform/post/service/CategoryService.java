package com.aform.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aform.post.domain.category.Category;
import com.aform.post.domain.category.CategoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;  
    
    @Transactional
    public Category createCategory(String categoryType){
        Category category = Category.builder().categoryType(categoryType).build();
        return categoryRepository.save(category);
    }

    @Transactional
    public Long deleteCategory(Long categoryPk){
        categoryRepository.deleteByCategoryPk(categoryPk);
        return categoryPk;
    }

    @Transactional
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
}
