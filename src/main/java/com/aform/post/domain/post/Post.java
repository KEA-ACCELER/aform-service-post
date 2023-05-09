package com.aform.post.domain.post;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.aform.post.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor (access = lombok.AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "post_pk")
    private Long postPk;

    @Column(name = "post_author")
    private String postAuthor; // 설문 등록자

    @Column(name = "post_title", length = 100)
    private String postTitle;

    @Column(name = "post_desc", length = 1000)
    private String PostDesc;

    @Column(name = "post_survey")
    private Long postSurvey; // 설문지의 pk만 저장하다, 내용 자체는 요청하는 방식

    @Column(name = "post_views")
    private int postViews;

    @Builder
    public Post(String postAuthor, String postTitle, String PostDesc, Long postSurvey, int postViews){
        this.postAuthor = postAuthor;
        this.postTitle = postTitle;
        this.PostDesc = PostDesc;
        this.postSurvey = postSurvey;
        this.postViews = postViews;
    }



}
