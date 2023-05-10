package com.aform.post.web.dto;

import com.aform.post.domain.post.Post;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PostDto {
    @Getter
    @RequiredArgsConstructor
    public static class PostCreateRequestDto{
        String postTitle;
        String postDesc;
        Long postSurvey;
    
        public Post toEntity(String author){
            return Post.builder()
                .postTitle(postTitle)
                .postDesc(postDesc)
                .postSurvey(postSurvey)
                .postAuthor(author)
                .postViews(0) // initial value
                .build();
        }
    }  
    
    @Getter
    @RequiredArgsConstructor
    public static class PostResponseDto{
        Long postPk;
        String postTitle;
        String postDesc;
        Long postSurvey;
        int postViews;
    
        @Builder
        public PostResponseDto(Post post){
            this.postPk = post.getPostPk();
            this.postTitle = post.getPostTitle();
            this.postDesc = post.getPostDesc();
            this.postSurvey = post.getPostSurvey();
            this.postViews = post.getPostViews();
        }

    }

    @Getter
    @RequiredArgsConstructor
    public static class PostListResponseDto{
        Long postPk;
        String postTitle;
        int postViews;

        @Builder
        public PostListResponseDto(Post post){
            this.postPk = post.getPostPk();
            this.postTitle = post.getPostTitle();
            this.postViews = post.getPostViews();
        }
    }
}
