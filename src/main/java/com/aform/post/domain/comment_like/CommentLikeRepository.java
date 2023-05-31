package com.aform.post.domain.comment_like;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aform.post.domain.comment.Comment;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByCommentLikeUserAndCommentLikeComment(Long commentLikeUser, Comment commentLikeComment);

    void deleteByCommentLikeUserAndCommentLikeComment(Long userPk, Comment comment);

    List<CommentLike> findAllByCommentLikeComment(Comment comment);

}
