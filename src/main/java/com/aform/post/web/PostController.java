package com.aform.post.web;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private FeignService feignService;

    @Autowired
    private PostService postService;

    // @GetMapping("/user/info")
    // public ResponseEntity<Optional<GetUserResponseDto>> getUser(HttpServletRequest request){
    //     return ResponseEntity.ok(feignService.getUserInfo(request));
    // }

    // @GetMapping("/test")
    // public ResponseEntity<String> test(HttpServletRequest request){
    //     return ResponseEntity.ok(feignService.test());
    // }

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

    @GetMapping("/getPost/{postPk}")
    public ResponseEntity<PostResponseDto> getOnePost(@PathVariable("postPk") Long postPk){
        return ResponseEntity.ok(postService.getOnePost(postPk));
    }

    @GetMapping("/getPosts/{itemNum}/{pageIndex}")
    public ResponseEntity<List<PostListResponseDto>> get10Post(@PathVariable(value="pageIndex") int index, @PathVariable(value="itemNum") int itemNum){
        return ResponseEntity.ok(postService.getPostList(index, itemNum));
    }
    
    @GetMapping("/getUserPosts/{userPk}/{itemNum}/{pageIndex}")
    public ResponseEntity<List<PostListResponseDto>> getUserPosts(@PathVariable(value="userPk") Long userPk, @PathVariable(value="pageIndex") int index, @PathVariable(value="itemNum") int itemNum){
        return ResponseEntity.ok(postService.getUserPostList(userPk, index, itemNum));
    }

    @PostMapping("/updateViews/{postPk}")
    public ResponseEntity<Post> updateViews(@PathVariable("postPk") Long postPk){
        return ResponseEntity.ok(postService.updateViews(postPk));
    }

    // @GetMapping("/getSurveyContent/{surveyId}")
    // public ResponseEntity<Object> getSurvey(@PathVariable(value="surveyId") String surveyId){
    //     log.info("surveyId  in Post Controller : " +surveyId);
    //     ResponseEntity<Object> result = postService.getSurvey(surveyId);
    //     return result;
    // }
    
    @GetMapping("/getUserPostsCnt/{userPk}")
    public ResponseEntity<Long> getUserPostsCnt(@PathVariable(value="userPk") Long userPk){
        return ResponseEntity.ok(postService.getUserPostsCnt(userPk));
    }

    @GetMapping("/getPopularPost")
    public ResponseEntity<List<PostListResponseDto>> getPopularPost(@RequestParam(value="localDateTime") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime localDateTime){
        return ResponseEntity.ok(postService.getPopularPost(localDateTime));
    }

    @GetMapping("/getABposts")
    public ResponseEntity<List<PostListResponseDto>> getABposts(){
        return ResponseEntity.ok(postService.getABposts());
    }

    @GetMapping(value="/getNORMALposts")
    public ResponseEntity<List<PostListResponseDto>> getNORMALposts(){
        return ResponseEntity.ok(postService.getNORMALposts());
    }
    
}
