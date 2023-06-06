package com.aform.post.domain.post;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aform.post.web.dto.PostDto.PostListResponseDto;

public interface PostRepository extends JpaRepository<Post, Long>{
    
    public void deleteByPostPk(Long postPk);

    public Post findByPostPk(Long postPk);
    
    public Page<Post> findAllByPostAuthor(Long userPk, Pageable pageable);

    public List<Post> findAllByPostAuthor(Long userPk);

    public Optional<Post> findFirstByPostSurvey(String surveyPk);

    public List<Post> findAllByPostSurveyType(String string);

    

}
