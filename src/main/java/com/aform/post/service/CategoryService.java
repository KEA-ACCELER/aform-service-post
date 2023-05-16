package com.aform.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aform.post.domain.category.Category;
import com.aform.post.domain.category.CategoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;  
    
    @Transactional
    public Category createCategory(String categoryType){
        if (categoryRepository.findByCategoryType(categoryType).isPresent()) {
            log.info("이미 존재하는 카테고리입니다.");
            return null;
        }

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
