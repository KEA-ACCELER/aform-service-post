package com.aform.post.web.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SurveyDto {
   @Getter
   @NoArgsConstructor
   public static class GetOneSurvey{
    private String _id;
    private String type;
    private String title;
    private String author;
    private LocalDateTime deadline;
    private String[] questions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private String description;
    private String[] statistics;

    @Builder
    public GetOneSurvey(String _id, String type, String title, String author, LocalDateTime deadline, String[] questions, LocalDateTime createdAt, LocalDateTime updatedAt, String status, String description, String[] statistics) {
        this._id = _id;
        this.type = type;
        this.title = title;
        this.author = author;
        this.deadline = deadline;
        this.questions = questions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.description = description;
        this.statistics = statistics;
    }

}

}
