package com.aform.post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyRequestInterceptor implements RequestInterceptor{

    @Setter
    private String jwt;

    @Override
    public void apply(RequestTemplate template) {
        // TODO Auto-generated method stub
        log.info("jwt: "+this.jwt);
        template.header("authorization"  ,"Bearer ", this.jwt);
    }
    
    
}
