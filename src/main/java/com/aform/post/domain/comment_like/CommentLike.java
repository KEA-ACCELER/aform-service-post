package com.aform.post.domain.comment_like;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.aform.post.BaseTimeEntity;
import com.aform.post.domain.comment.Comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Table(name = "comment_like")
@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class CommentLike extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "comment_like_pk")
    private Long commentLikePk;

    @Column(name = "comment_like_user")
    private String commentLikeUser; //좋아요 누른사람 정보저장

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "comment_like_comment")
    private Comment commentLikeComment; // 좋아요가 눌린 댓글

    @Builder
    public CommentLike(String commentLikeUser, Comment commentLikeComment) {
        this.commentLikeUser = commentLikeUser;
        this.commentLikeComment = commentLikeComment;
    }
}
