package com.aform.post.web.dto;

import com.aform.post.domain.category.Category;
import com.aform.post.domain.post.Post;
import com.aform.post.domain.post_category.PostCategory;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PostCategoryDto {
    
    @Getter
    @RequiredArgsConstructor
    public static class PostCategoryCreateRequestDto {
        //category 정보
        private String categoryType;
        //postCategory 정보
        private Long postPk;

        public PostCategory toEntity(Category category, Post post){
            return PostCategory.builder()
                    .postCategoryCategory(category)
                    .postCategoryPost(post)
                    .build();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class GetPostCategoryResponseDto {
        private Long postCategoryPk;
        private Category postCategoryCategory;
        private Long postCategoryPostPk;

        @Builder
        public GetPostCategoryResponseDto(PostCategory postCategory){
            this.postCategoryPk = postCategory.getPostCategoryPk();
            this.postCategoryCategory = postCategory.getPostCategoryCategory();
            this.postCategoryPostPk = postCategory.getPostCategoryPost().getPostPk();
        }
    }

}
