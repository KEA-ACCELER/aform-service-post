package com.aform.post.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aform.post.domain.category.Category;
import com.aform.post.domain.category.CategoryRepository;
import com.aform.post.domain.post_category.PostCategory;
import com.aform.post.domain.post_category.PostCategoryRepository;
import com.aform.post.web.dto.PostCategoryDto.GetPostCategoryResponseDto;
import com.aform.post.web.dto.PostCategoryDto.PostCategoryCreateRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostCategoryService {
    
    @Autowired  
    PostCategoryRepository postCategoryRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Transactional
    public PostCategory createPostCategory(PostCategoryCreateRequestDto postCategoryCreateRequestDto) {
        /*
         * Category 데이터를 찾아서
         * 
         * 새로운 카테고리면 추가하고
         * postcategory에도 추가하고
         * 
         * 기존에 있던거면
         * postcategory에만 추가면
         *
         */
        Category category = categoryService.createCategory(postCategoryCreateRequestDto.getCategoryType());
        if (category == null) { // 이미 존재하는 카테고리이면 연관관계만 가아서 추가
            Long existedCategoryPk = categoryRepository.findByCategoryType(postCategoryCreateRequestDto.getCategoryType()).get().getCategoryPk();
            return postCategoryRepository.save(postCategoryCreateRequestDto.toEntity(existedCategoryPk));
        }
        else { // 새로운 카테고리가 추가되었으면 연관관계 추가해서 저장
            return postCategoryRepository.save(postCategoryCreateRequestDto.toEntity(category.getCategoryPk()));
        }
    }

    @Transactional
	public List<GetPostCategoryResponseDto> getPostCategories(Long postPk) {
        List<PostCategory> postCategories = postCategoryRepository.findAllByPostPk(postPk);
        return postCategories.stream()
                .map(item -> GetPostCategoryResponseDto.builder().postCategory(item).build())
                .collect(Collectors.toList());
	}

    @Transactional
    public Long deletePostCategory(Long postCategoryPk){
        postCategoryRepository.deleteByPostCategoryPk(postCategoryPk);
        return postCategoryPk;
    }


}
