package com.aform.post.web;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.scripting.groovy.GroovyObjectCustomizer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aform.post.domain.post.Post;
import com.aform.post.service.FeignService;
import com.aform.post.service.PostService;
import com.aform.post.web.dto.SurveyDto;
import com.aform.post.web.dto.PostDto.PostCreateRequestDto;
import com.aform.post.web.dto.PostDto.PostListResponseDto;
import com.aform.post.web.dto.PostDto.PostResponseDto;
import com.aform.post.web.dto.SurveyDto.GetOneSurvey;
import com.aform.post.web.dto.UserDto.GetUserResponseDto;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/app/post")
public class PostController {

    @Autowired
    private FeignService feignService;

    @Autowired
    private PostService postService;

    @GetMapping("/user/info")
    public ResponseEntity<Optional<GetUserResponseDto>> getUser(HttpServletRequest request){
        return ResponseEntity.ok(feignService.getUserInfo(request));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(HttpServletRequest request){
        return ResponseEntity.ok(feignService.test());
    }

    //--------------------post down--------------------

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(HttpServletRequest request, @RequestBody PostCreateRequestDto postCreateRequestDto){
        //String userId = feignService.getUserInfo(request).get().getUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(postCreateRequestDto));
    }
    
    @DeleteMapping("/delete/{postPk}")
    public ResponseEntity<String> deletePost(@PathVariable("postPk") Long postPk){
        return ResponseEntity.ok("Deleted : "+postService.deletePost(postPk));
    }

    @GetMapping("/get/{postPk}")
    public ResponseEntity<PostResponseDto> getOnePost(@PathVariable("postPk") Long postPk){
        return ResponseEntity.ok(postService.getOnePost(postPk));
    }

    @GetMapping("/getPosts/{itemNum}/{pageIndex}")
    public ResponseEntity<List<PostListResponseDto>> get10Post(@PathVariable(value="pageIndex") int index, @PathVariable(value="itemNum") int itemNum){
        return ResponseEntity.ok(postService.getPostList(index, itemNum));
    }
    
//--------------------
    @GetMapping("/getSurvey/{surveyId}")
    public ResponseEntity<Object> getSurvey(@PathVariable(value="surveyId") String surveyId){
        log.info("surveyId  in Post Controller : " +surveyId);
        ResponseEntity<Object> result = postService.getSurvey(surveyId);
        return result;
    }

}
