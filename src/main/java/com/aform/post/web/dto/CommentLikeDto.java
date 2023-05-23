package com.aform.post.web.dto;

import com.aform.post.domain.comment.Comment;
import com.aform.post.domain.comment_like.CommentLike;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CommentLikeDto {
    @Getter
    @RequiredArgsConstructor
    public static class CommentLikeCreateRequestDto {
        private Long commentLikeUser;
        private Long commentPk;

        public CommentLike toEntity(Comment comment) {
            return CommentLike.builder()
                    .commentLikeUser(commentLikeUser)
                    .commentLikeComment(comment)
                    .build();
        }

    }
    
}
