package com.aform.post.domain.post_category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long>{

    List<PostCategory> findAllByPostPk(Long postPk);

    void deleteByPostCategoryPk(Long postCategoryPk);
    
}
