package com.aform.post.domain.post_category;

import java.io.Serializable;

import com.aform.post.BaseTimeEntity;
import com.aform.post.domain.category.Category;
import com.aform.post.domain.post.Post;

import jakarta.persistence.*;
import lombok.Builder;

public class PostCategory extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "post_category_pk")
    private Long postCategoryPk;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "post_category_post")
    private Post postCategoryPost; // 카테고리가 속한 게시글


    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "post_category_category")
    private Category postCategoryCategory; // 카테고리가 속한 게시글
 
    @Builder
    public PostCategory(Post postCategoryPost, Category postCategoryCategory) {
        this.postCategoryPost = postCategoryPost;
        this.postCategoryCategory = postCategoryCategory;
    }
}
