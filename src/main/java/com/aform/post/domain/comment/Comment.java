package com.aform.post.domain.comment;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.aform.post.BaseTimeEntity;
import com.aform.post.domain.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Component
@Table(name = "comment")
@NoArgsConstructor (access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Comment extends BaseTimeEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "comment_pk")
    private Long commentPk;

    @Column(name = "comment_author")
    private String commentAuthor;

    @Column(name = "comment_content", length = 500)
    private String commentContent;

    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "comment_post")
    private Post commentPost;

    @Column(name = "comment_like")
    private int commentLike;


    @Builder
    public Comment(String commentAuthor, String commentContent, Post commentPost, int commentLike) {
        this.commentAuthor = commentAuthor;
        this.commentContent = commentContent;
        this.commentPost = commentPost;
        this.commentLike = commentLike;
    }
    
}
