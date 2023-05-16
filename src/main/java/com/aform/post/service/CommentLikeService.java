package com.aform.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aform.post.domain.comment.Comment;
import com.aform.post.domain.comment.CommentRepository;
import com.aform.post.domain.comment_like.CommentLike;
import com.aform.post.domain.comment_like.CommentLikeRepository;
import com.aform.post.web.dto.CommentLikeDto.CommentLikeCreateRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentLikeService {
    @Autowired
    CommentLikeRepository commentLikeRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public CommentLike createCommentLike(CommentLikeCreateRequestDto commentLikeCreateRequestDto) {
        
        //해당 댓글에 좋아요를 누른 유저인지 확인
        if (commentLikeRepository.findByCommentLikeUserAndCommentLikeComment(commentLikeCreateRequestDto.getCommentLikeUser(), commentLikeCreateRequestDto.getCommentLikeComment()).isPresent()) {
            log.info("이미 좋아요를 누른 유저입니다.");
            return null;
        }
        // 댓글 좋아요 수 증가
        commentService.updateCommentLikeCount(commentLikeCreateRequestDto.getCommentLikeComment());

        return commentLikeRepository.save(commentLikeCreateRequestDto.toEntity());
    }

    public CommentLike deleteCommentLike(Long commentPk, Long userPk) {
        Comment comment = commentRepository.findByCommentPk(commentPk);
        return commentLikeRepository.deleteByCommentLikeUserAndCommentLikeComment(userPk, comment);
    }

}
