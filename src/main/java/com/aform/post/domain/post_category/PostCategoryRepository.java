package com.aform.post.domain.post_category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aform.post.domain.post.Post;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Long>{

    List<PostCategory> findAllByPostCategoryPost(Post postPk);

    void deleteByPostCategoryPk(Long postCategory);
    
}
