package com.aform.post.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aform.post.domain.comment.Comment;
import com.aform.post.domain.comment.CommentRepository;
import com.aform.post.domain.post.PostRepository;
import com.aform.post.domain.post.Post;
import com.aform.post.web.dto.CommentDto;
import com.aform.post.web.dto.CommentDto.CommentCreateRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;
  
    @Transactional
	public Comment createComment(CommentCreateRequestDto commentCreateRequestDto) {
		Post post = postRepository.findByPostPk(commentCreateRequestDto.getCommentPost());
        log.info("post"+ post);
        if(post == null){
        log.info("해당 게시글이 없습니다. id=" + commentCreateRequestDto.getCommentPost());
        }
        return commentRepository.save(commentCreateRequestDto.toEntity(post));
	}
    
    @Transactional
    public List<CommentDto.GetCommentResponseDto> getComments(Long commentPost, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        Post post = postRepository.findByPostPk(commentPost);
        Page<Comment> result = commentRepository.findByCommentPost(post, pageable);
        return result.getContent()
            .stream()
            .map(comment -> CommentDto.GetCommentResponseDto.builder().entity(comment).build())
            .collect(Collectors.toList());
    }

    @Transactional
    public Long deleteComment(Long commentPk){
        commentRepository.deleteByCommentPk(commentPk);
        return commentPk;
    }

    @Transactional
    public Comment updateCommentLikeCount(Comment comment, Long num){
        Long commentLike = comment.getCommentLike();
        log.info("commentLike : "+ commentLike);
        comment.setCommentLike(commentLike+num);
        return commentRepository.save(comment);
    }

    @Transactional
    public int getCommentCnt(Long postPk) {
        Post post = postRepository.findByPostPk(postPk);
        return commentRepository.findAllByCommentPost(post).size();
    
    }
}
