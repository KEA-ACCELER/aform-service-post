package com.aform.post.domain.comment_like;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aform.post.domain.comment.Comment;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<Comment> findByCommentLikeUserAndCommentLikeComment(Long commentLikeUser, Long commentLikeComment);
   //public boolean existsByCommentLikeUser(Long commentLikeUser); 

    CommentLike deleteByCommentLikeUserAndCommentLikeComment(Long userPk, Long commentPk);

}
