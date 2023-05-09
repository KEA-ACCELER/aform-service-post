package com.aform.post.web;

import java.net.http.HttpRequest;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aform.post.config.MyRequestInterceptor;
import com.aform.post.service.PostService;
import com.aform.post.web.dto.UserDto.GetUserResponseDto;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/app/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/user/info")
    public ResponseEntity<Optional<GetUserResponseDto>> getUser(HttpServletRequest request){
        return ResponseEntity.ok(postService.getUserInfo(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(HttpServletRequest request){
        return ResponseEntity.ok(postService.test());
    }
}
