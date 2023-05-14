package com.aform.post.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aform.post.domain.comment_like.CommentLike;
import com.aform.post.service.CommentLikeService;
import com.aform.post.web.dto.CommentLikeDto.CommentLikeCreateRequestDto;

@Controller
@RequestMapping("/api/commentlike")
public class CommentLikeController {

    @Autowired
    CommentLikeService commentLikeService;
    
    @PostMapping("/create")
    public ResponseEntity<CommentLike> createCommentLike(@RequestBody CommentLikeCreateRequestDto commentLikeCreateRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentLikeService.createCommentLike(commentLikeCreateRequestDto));
    }

    @DeleteMapping("/delete/{commentPk}/{userPk}")
    public ResponseEntity<CommentLike> deleteCommentLike(@PathVariable("commentPk") Long commentPk, @PathVariable("userPk") Long userPk){
        return ResponseEntity.status(HttpStatus.OK).body(commentLikeService.deleteCommentLike(commentPk, userPk));
    }

}