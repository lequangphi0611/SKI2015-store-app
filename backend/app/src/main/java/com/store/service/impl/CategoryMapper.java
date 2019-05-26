package com.store.service.impl;

import com.store.entity.Category;
import com.store.model.CategoryDTO;
import com.store.service.Mapper;

import org.springframework.stereotype.Service;

@Service(value = "cateoryMapper")
public class CategoryMapper implements Mapper<CategoryDTO, Category> {

    @Override
    public CategoryDTO mapDto(Category entity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(entity.getId());
        categoryDTO.setName(entity.getName());
        return categoryDTO;
    }

    @Override
    public Category mapEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        return category;
    }

    
}