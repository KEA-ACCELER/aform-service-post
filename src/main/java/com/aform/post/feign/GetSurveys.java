package com.aform.post.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.websocket.server.PathParam;

@FeignClient(
    name = "survey-service", 
    url = "${survey.server.url}}"
)
public interface GetSurveys {
    
    @GetMapping(path="/{surveyid}")
    public Object getSurvey(@PathVariable("surveyid") Long surveyId);

}
