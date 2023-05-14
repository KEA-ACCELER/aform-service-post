package com.aform.post.domain.post_category;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.aform.post.BaseTimeEntity;
import com.aform.post.domain.category.Category;
import com.aform.post.domain.post.Post;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_category")
@Component
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class PostCategory extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "post_category_pk")
    private Long postCategoryPk;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "post_category_post")
    private Long postCategoryPost; // 카테고리가 속한 게시글


    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "post_category_category")
    private Long postCategoryCategory; // 카테고리가 속한 게시글
 
    @Builder
    public PostCategory(Long postCategoryPost, Long postCategoryCategory) {
        this.postCategoryPost = postCategoryPost;
        this.postCategoryCategory = postCategoryCategory;
    }
}
