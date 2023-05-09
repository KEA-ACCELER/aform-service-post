package com.aform.post.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class BearerAuthFeignConfig {
    static @Setter
    String jwt;

    public RequestInterceptor bearerAuthRequestInterceptor() {
        return requestTemplate -> {
            if (jwt != null) {
                requestTemplate.header("Authorization", "Bearer " + jwt);
            } else {
                log.error("Unable to add Authoriation header to Feign requestTemplate");
            }
        };
    }
    
}
