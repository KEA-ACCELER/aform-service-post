package com.aform.post.domain.post;

import java.io.Serializable;
import java.time.LocalDateTime;

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
import lombok.Setter;

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
    private Long postAuthor; // 설문 등록자

    @Column(name = "post_title", length = 100)
    private String postTitle;

    @Column(name = "post_desc", length = 1000)
    private String postDesc;

    @Column(name = "post_survey")
    private String postSurvey; // 설문지의 pk만 저장하다, 내용 자체는 요청하는 방식

    @Setter
    @Column(name = "post_views")
    private Long postViews;

    @Column(name = "post_start_date") //It is not same with createdDate, it could be not from now.
    private LocalDateTime postStartDate;

    @Column(name = "post_due_date")
    private LocalDateTime postDueDate; 

    @Builder
    public Post(Long postAuthor, String postTitle, String postDesc, String postSurvey, Long postViews, LocalDateTime postStartDate, LocalDateTime postDueDate){
        this.postAuthor = postAuthor;
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postSurvey = postSurvey;
        this.postViews = postViews;
        this.postStartDate = postStartDate;
        this.postDueDate = postDueDate;
    }



}
