package com.aform.post.domain.post_category;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aform.post.domain.category.Category;
import com.aform.post.domain.post.Post;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long>{

    List<PostCategory> findAllByPostCategoryPost(Post postPk);

    void deleteByPostCategoryPk(Long postCategoryPk);

    Optional<PostCategory> findByPostCategoryPostAndPostCategoryCategory(Post PostPk, Category category);
    
}
