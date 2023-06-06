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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aform.post.domain.comment.Comment;
import com.aform.post.service.CommentService;
import com.aform.post.web.dto.CommentDto;

@RestController
@RequestMapping("/api/post/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto.CommentCreateRequestDto commentCreateRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentCreateRequestDto));
    }

    @GetMapping("/get/comments/{postPk}/{size}/{page}") //해당 게시글의 모든 댓글 조회, 페이지네이션 적용
    public ResponseEntity<List<CommentDto.GetCommentResponseDto>> getComments(@PathVariable(value = "postPk") Long commentPost, @PathVariable(value = "size") int size, @PathVariable(value = "page") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComments(commentPost, size, page));
    }

    @DeleteMapping("/delete/{commentPk}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "commentPk") Long commentPk) {
        Long i = commentService.deleteComment(commentPk);
        return ResponseEntity.status(HttpStatus.OK).body(i+ "번째 댓글 삭제");
    }

    @GetMapping("/commentCnt/{postPk}")
    public ResponseEntity<String> getCommentCnt(@PathVariable(value = "postPk") Long postPk) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentCnt(postPk)+"");
    }
}
