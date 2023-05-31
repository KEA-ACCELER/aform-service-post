package com.aform.post.domain.comment;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.aform.post.BaseTimeEntity;
import com.aform.post.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
import lombok.Setter;
import lombok.ToString;

@Entity
@Component
@Table(name = "comment")
@NoArgsConstructor (access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class Comment extends BaseTimeEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "comment_pk")
    private Long commentPk;

    @Column(name = "comment_author")
    private Long commentAuthor;

    @Column(name = "comment_content", length = 500)
    private String commentContent;


    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "comment_post")
    private Post commentPost;

    @Setter
    @Column(name = "comment_like")
    private Long commentLike;


    @Builder
    public Comment(Long commentAuthor, String commentContent, Post commentPost, Long commentLike) {
        this.commentAuthor = commentAuthor;
        this.commentContent = commentContent;
        this.commentPost = commentPost;
        this.commentLike = commentLike;
    }
    
}
