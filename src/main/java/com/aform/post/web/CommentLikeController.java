package com.aform.post.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aform.post.domain.comment_like.CommentLike;
import com.aform.post.service.CommentLikeService;
import com.aform.post.web.dto.CommentLikeDto.CommentLikeCreateRequestDto;
import com.aform.post.web.dto.CommentLikeDto.CommentLikeUserListItem;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/commentLike")
@Slf4j
public class CommentLikeController {

    @Autowired
    CommentLikeService commentLikeService;

    @PostMapping("/likeButtonClicked")
    public ResponseEntity<CommentLike> createCommentLike(
            @RequestBody CommentLikeCreateRequestDto commentLikeCreateRequestDto) {
        log.info(commentLikeCreateRequestDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentLikeService.createDeleteCommentLike(commentLikeCreateRequestDto));
    }

    @GetMapping("/wholikedComment/{postPk}")
    public ResponseEntity<List<CommentLikeUserListItem>> getCommentLikeCount(@PathVariable(value = "postPk") Long postPk) {
        return ResponseEntity.status(HttpStatus.OK).body(commentLikeService.getCommentLikeUserList(postPk));
    }
}