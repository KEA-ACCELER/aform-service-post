package com.aform.post.web.dto;

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

        public PostCategory toEntity(Long categoryPk){
            return PostCategory.builder()
                    .postCategoryCategory(categoryPk)
                    .postCategoryPost(postPk)
                    .build();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class GetPostCategoryResponseDto {
        private Long postCategoryPk;
        private Long postCategoryCategory;
        private Long postCategoryPost;

        @Builder
        public GetPostCategoryResponseDto(PostCategory postCategory){
            this.postCategoryPk = postCategory.getPostCategoryPk();
            this.postCategoryCategory = postCategory.getPostCategoryCategory();
            this.postCategoryPost = postCategory.getPostCategoryPost();
        }
    }

}
