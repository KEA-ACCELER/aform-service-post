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

import com.aform.post.domain.post_category.PostCategory;
import com.aform.post.service.PostCategoryService;
import com.aform.post.web.dto.PostCategoryDto.GetPostCategoryResponseDto;
import com.aform.post.web.dto.PostCategoryDto.PostCategoryCreateRequestDto;

@RestController
@RequestMapping("/api/post/postCategory")
public class PostCategoryController {

    @Autowired
    PostCategoryService postCategoryService;

    @PostMapping("/create")
    public ResponseEntity<PostCategory> createPostCategory(@RequestBody PostCategoryCreateRequestDto postCategoryCreateRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(postCategoryService.createPostCategory(postCategoryCreateRequestDto));
    }

    @GetMapping("/get/{postPk}")
    public ResponseEntity<List<GetPostCategoryResponseDto>> getPostCategory(@PathVariable(value = "postPk") Long postPk){
        return ResponseEntity.status(HttpStatus.OK).body(postCategoryService.getPostCategories(postPk));
    }

    @DeleteMapping("/delete/{postCategoryPk}")
    public ResponseEntity<String> deletePostCategory(@PathVariable(value = "postCategoryPk") Long postCategoryPk){
        Long deletedItem = postCategoryService.deletePostCategory(postCategoryPk);
        return ResponseEntity.status(HttpStatus.OK).body(deletedItem+" is deleted");
    }
    
}
