package com.aform.post.feign;

import java.time.LocalDateTime;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.parameters.RequestBody;




@FeignClient(
    name = "survey-service", 
    url = "${survey.server.url}"
)
public interface GetSurveys {
    
    @GetMapping(path="/{_id}")
    public ResponseEntity<Object> getSurvey(@PathVariable(value="_id") String surveyId);

    @GetMapping(path="/popular")
    public ResponseEntity<String[]> getPopularSurvey(@RequestParam(value="date") String date , 
                                        @RequestParam(value="type") String id);   
}
