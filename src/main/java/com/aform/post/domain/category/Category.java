package com.aform.post.domain.category;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.aform.post.BaseTimeEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Entity
@Table(name = "category")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Category  extends BaseTimeEntity implements Serializable{
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "category_pk")
    private Long categoryPk;

    @Column(name = "category_type")
    private String categoryType; // 카테고리 타입

    @Builder
    public Category(String categoryType) {
        this.categoryType = categoryType;
    }
}
