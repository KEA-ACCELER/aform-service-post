package com.aform.post.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>{
    
    public void deleteByPostPk(Long postPk);

    public Post findByPostPk(Long postPk);

}
