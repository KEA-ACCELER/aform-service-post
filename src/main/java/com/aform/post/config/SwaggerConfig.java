package com.aform.post.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
            .title("Aform Post API")
            .description("Aform Post API")
            .version("1.0.0");
        
            return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    @Bean
    public GroupedOpenApi postGroup(){
        return GroupedOpenApi.builder()
                .group("post")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Post API").version("1.0.0").description("Post API")))
                .packagesToScan("com.aform.post")
                .build();
    }
}
