package com.aform.post.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aform.post.web.dto.SurveyDto;

import feign.Param;


@FeignClient(
    name = "survey-service", 
    url = "${survey.server.url}"
)
public interface GetSurveys {
    
    @GetMapping(path="/{_id}")
    public ResponseEntity<Object> getSurvey(@PathVariable(value="_id") String surveyId);

}
