package com.aform.post.domain.category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    void deleteByCategoryPk(Long categoryPk);

    Optional<Category> findByCategoryType(String categoryType);
    
}
