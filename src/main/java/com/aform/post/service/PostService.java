package com.aform.post.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aform.post.domain.post.Post;
import com.aform.post.domain.post.PostRepository;
import com.aform.post.feign.GetSurveys;
import com.aform.post.web.dto.PostDto;
import com.aform.post.web.dto.PostDto.PostListResponseDto;
import com.aform.post.web.dto.SurveyDto.GetOneSurvey;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    GetSurveys getSurveys;
    
    @Transactional
    public Post createPost(PostDto.PostCreateRequestDto postCreateRequestDto ){
        return postRepository.save(postCreateRequestDto.toEntity());
    }

    @Transactional
    public Long deletePost(Long postPk){
        postRepository.deleteByPostPk(postPk);
        return postPk;
    } 

    @Transactional
    public PostDto.PostResponseDto getOnePost(Long postPk){
        Post post = postRepository.findByPostPk(postPk);
        return PostDto.PostResponseDto.builder().post(post).build();
    }

    @Transactional
    public List<PostListResponseDto> getPostList(int index, int itemNum){
        Pageable pageable = PageRequest.of(index, itemNum);
        Page<Post> result =postRepository.findAll(pageable); //페이징
        return result.getContent()
            .stream()
            .map(post -> PostListResponseDto.builder().post(post).build())
            .collect(Collectors.toList());


    }
    
//---------------------------------
    @Transactional
    public ResponseEntity<Object> getSurvey(String surveyId){
        if (surveyId == null) return ResponseEntity.badRequest().build();
        return getSurveys.getSurvey(surveyId);
    }
////////////----------------------
    @Transactional
    public List<PostListResponseDto> getUserPostList(Long userPk, int index, int itemNum){
        Pageable pageable = PageRequest.of(index, itemNum);
        Page<Post> result =postRepository.findAllByPostAuthor(userPk, pageable); //페이징
        return result.getContent()
            .stream()
            .map(post -> PostListResponseDto.builder().post(post).build())
            .collect(Collectors.toList());
    }

    @Transactional
    public Post updateViews(Long postPk) {
        Post post = postRepository.findByPostPk(postPk);
        post.setPostViews(post.getPostViews()+1L);
        return postRepository.save(post);
        
    } 

  

}
