package com.aform.post.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aform.post.domain.post.Post;
import com.aform.post.domain.post.PostRepository;
import com.aform.post.web.dto.PostDto;
import com.aform.post.web.dto.PostDto.PostListResponseDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    PostRepository postRepository;
    
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


}
