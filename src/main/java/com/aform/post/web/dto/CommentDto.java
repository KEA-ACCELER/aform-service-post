package com.aform.post.web.dto;

import java.time.LocalDateTime;

import com.aform.post.domain.comment.Comment;
import com.aform.post.domain.post.Post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class CommentDto {

    @Getter
    @RequiredArgsConstructor
    public static class CommentCreateRequestDto {
    
        private Long commentAuthor;
        private String commentContent;
        private Long commentPost;
    
        public Comment toEntity(Post post){
            return Comment.builder()
                    .commentAuthor(commentAuthor)
                    .commentContent(commentContent)
                    .commentPost(post)
                    .commentLike(0L) // Default value
                    .build();
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class GetCommentResponseDto {
    
        private Long commentPk;
        private Long commentAuthor;
        private String commentContent;
        private Long commentLike;
        private LocalDateTime createdDate;
    
        @Builder
        public GetCommentResponseDto(Comment entity) {
            this.commentPk = entity.getCommentPk();
            this.commentAuthor = entity.getCommentAuthor();
            this.commentContent = entity.getCommentContent();
            this.commentLike = entity.getCommentLike();
            this.createdDate = entity.getCreatedDate();
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static class UpdateCommentLikeRequestDto {
        private Long commentPk;
        private Long commentLike;

        @Builder
        public UpdateCommentLikeRequestDto(Comment entity) {
            this.commentPk = entity.getCommentPk();
            this.commentLike = entity.getCommentLike();
        }
    } 
}
