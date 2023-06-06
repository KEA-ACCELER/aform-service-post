package com.aform.post.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.aform.post.utils.TimezoneConverter;

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
        return PostDto.PostResponseDto.builder().post(post)
                                                .postStartDate(TimezoneConverter.UTC2KST(post.getPostStartDate()))
                                                .postDueDate(TimezoneConverter.UTC2KST(post.getPostDueDate()))
                                                .build();
    }

    @Transactional
    public List<PostListResponseDto> getPostList(int index, int itemNum){
        Pageable pageable = PageRequest.of(index, itemNum);
        Page<Post> result =postRepository.findAll(pageable); //페이징
        return result.getContent()
            .stream()
            .map(post -> PostListResponseDto.builder()
                                        .post(post).
                                        postStartDate(TimezoneConverter.UTC2KST(post.getPostStartDate())).
                                        postDueDate(TimezoneConverter.UTC2KST(post.getPostDueDate())).
                                        build())
            .collect(Collectors.toList());


    }
    
    // @Transactional
    // public ResponseEntity<Object> getSurvey(String surveyId){
    //     if (surveyId == null) return ResponseEntity.badRequest().build();
    //     return getSurveys.getSurvey(surveyId);
    // }

    @Transactional
    public List<PostListResponseDto> getUserPostList(Long userPk, int index, int itemNum){
        Pageable pageable = PageRequest.of(index, itemNum);
        Page<Post> result =postRepository.findAllByPostAuthor(userPk, pageable); //페이징
        return result.getContent()
            .stream()
            .map(post -> PostListResponseDto.builder().
                                            post(post).
                                            postStartDate(TimezoneConverter.UTC2KST(post.getPostStartDate())).
                                            postDueDate(TimezoneConverter.UTC2KST(post.getPostDueDate())).
                                            build())
            .collect(Collectors.toList());
    }

    @Transactional
    public Post updateViews(Long postPk) {
        Post post = postRepository.findByPostPk(postPk);
        post.setPostViews(post.getPostViews()+1L);
        return postRepository.save(post);
        
    }

    @Transactional
	public Long getUserPostsCnt(Long userPk) {
		List<Post> posts = postRepository.findAllByPostAuthor(userPk);
        return (long) posts.size();
	}

    @Transactional
    public List<PostListResponseDto> getPopularPost() {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atOffset(ZoneOffset.of("+09:00"));
        //9시간 더하기
        // localDateTime = localDateTime.plusHours(9);
        log.info("localDateTime : "+localDateTime.toString());
        if (getSurveys.getPopularSurvey(localDateTime.toString(), "id").getBody() == null) return null;
        String[] surveyPks = getSurveys.getPopularSurvey(localDateTime.toString(), "id").getBody();
        log.info("surveyPks : "+Arrays.toString(surveyPks));
        if(surveyPks.length == 0) return null;
        log.info("surveyPks : "+surveyPks[0]);
        List<Post> candidatePosts = new ArrayList<>();
        
        for (String surveyPk : surveyPks) {
            System.out.println("surveyPk in loop:"+surveyPk);

            Optional<Post> item = postRepository.findFirstByPostSurvey(surveyPk);
            if (item.isPresent()){
                {
                    candidatePosts.add(item.get());
                }
            }
        }
        log.info("candidatePost : "+candidatePosts.toString());

        List<PostListResponseDto> result = candidatePosts.stream()
            .map(post -> PostListResponseDto.builder().
                                            post(post).
                                            postStartDate(TimezoneConverter.UTC2KST(post.getPostStartDate())).
                                            postDueDate(TimezoneConverter.UTC2KST(post.getPostDueDate())).
                                            build())
            .collect(Collectors.toList());
        
        return result;
    }

    @Transactional
    public List<PostListResponseDto> getABposts() {
        return postRepository.findAllByPostSurveyType("AB")
            .stream()
            .map(post -> PostListResponseDto.builder().
                                            post(post).
                                            postStartDate(TimezoneConverter.UTC2KST(post.getPostStartDate())).
                                            postDueDate(TimezoneConverter.UTC2KST(post.getPostDueDate())).
                                            build())
            .collect(Collectors.toList());
    }

    @Transactional
    public List<PostListResponseDto> getNORMALposts() {
        return postRepository.findAllByPostSurveyType("NORMAL")
            .stream()
            .map(post -> PostListResponseDto.builder().
                                            post(post).
                                            postStartDate(TimezoneConverter.UTC2KST(post.getPostStartDate())).
                                            postDueDate(TimezoneConverter.UTC2KST(post.getPostDueDate())).
                                            build())
            .collect(Collectors.toList());
    } 

  

}
