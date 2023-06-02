package com.aform.post.domain.post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
    
    public void deleteByPostPk(Long postPk);

    public Post findByPostPk(Long postPk);
    
    public Page<Post> findAllByPostAuthor(Long userPk, Pageable pageable);

    public List<Post> findAllByPostAuthor(Long userPk);

    public Optional<List<Post>> findAllByPostSurvey(String postSurvey);

}
