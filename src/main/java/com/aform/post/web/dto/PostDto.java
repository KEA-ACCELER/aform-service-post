package com.aform.post.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        String postSurvey;
        Long author;
        String authorId;
        LocalDateTime postStartDate;
        LocalDateTime postDueDate;
        String postSurveyType;
    
        public Post toEntity(){
            return Post.builder()
                .postTitle(postTitle)
                .postDesc(postDesc)
                .postSurvey(postSurvey)
                .postAuthor(author)
                .postAuthorId(authorId)
                .postViews(0L) // initial value
                .postStartDate(postStartDate)
                .postDueDate(postDueDate)
                .postSurveyType(postSurveyType)
                .build();
        }
    }  
    
    @Getter
    @RequiredArgsConstructor
    public static class PostResponseDto{
        Long postAuthor;
        String postAuthorId;
        Long postPk;
        String postTitle;
        String postDesc;
        String postSurvey;
        Long postViews;
        LocalDateTime postStartDate;
        LocalDateTime postDueDate;
        String postSurveyType;
    
        @Builder
        public PostResponseDto(Post post, LocalDateTime postStartDate, LocalDateTime postDueDate){
            this.postAuthor = post.getPostAuthor();
            this.postAuthorId = post.getPostAuthorId();
            this.postPk = post.getPostPk();
            this.postTitle = post.getPostTitle();
            this.postDesc = post.getPostDesc();
            this.postSurvey = post.getPostSurvey();
            this.postViews = post.getPostViews();
            this.postSurveyType = post.getPostSurveyType();
            this.postStartDate = postStartDate;
            this.postDueDate = postDueDate;
        }

    }

    @Getter
    @RequiredArgsConstructor
    public static class PostListResponseDto{
        Long postAuthor;
        String postAuthorId;
        Long postPk;
        String postTitle;
        Long postViews;
        String postSurveyType;
        LocalDateTime postStartDate;
        LocalDateTime postDueDate;  

        @Builder
        public PostListResponseDto(Post post, LocalDateTime postStartDate, LocalDateTime postDueDate){
            this.postAuthor = post.getPostAuthor();
            this.postAuthorId = post.getPostAuthorId();
            this.postPk = post.getPostPk();
            this.postTitle = post.getPostTitle();
            this.postViews = post.getPostViews();
            this.postSurveyType = post.getPostSurveyType();
            this.postStartDate = postStartDate;
            this.postDueDate = postDueDate;
        }
    }
}
