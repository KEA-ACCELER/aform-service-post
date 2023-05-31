package com.aform.post.service;

import java.util.Optional;

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
    public CommentLike createDeleteCommentLike(CommentLikeCreateRequestDto commentLikeCreateRequestDto) {
        log.info("commentLikeUser: "+ commentLikeCreateRequestDto.getCommentLikeUser());
        log.info("commentPk: "+ commentLikeCreateRequestDto.getCommentPk());

        Comment comment = commentRepository.findByCommentPk(commentLikeCreateRequestDto.getCommentPk());
        //log.info(comment.toString());
        //해당 댓글에 좋아요를 누른 유저인지 확인, 눌렀으면 싫어요로 바꾸고 댓글 좋아요 수 감소
        Optional<CommentLike> commentLike = commentLikeRepository.findByCommentLikeUserAndCommentLikeComment(commentLikeCreateRequestDto.getCommentLikeUser(), comment);
        //log.info(commentLike.toString());
        if (commentLike.isPresent()){
            Comment result = commentService.updateCommentLikeCount(comment, -1L);
            commentLikeRepository.delete(commentLike.get());
            return null;
        }
        // 댓글 좋아요 수 증가
        else{
            Comment result = commentService.updateCommentLikeCount(comment, +1L);
            return commentLikeRepository.save(commentLikeCreateRequestDto.toEntity(result));
        }
    }


}
