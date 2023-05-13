package com.aform.post.domain.comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aform.post.domain.post.Post;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    public Comment findByCommentPk(Long commentPk);
    public Page<Comment> findByCommentPost(Post commentPost, Pageable pageable);
    public void deleteByCommentPk(Long commentPk);
}
