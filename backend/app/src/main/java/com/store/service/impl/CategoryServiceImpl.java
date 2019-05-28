package com.store.service.impl;

import com.store.entity.Category;
import com.store.model.CategoryDTO;
import com.store.respository.CategoryRepository;
import com.store.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends AbstracCrudService<CategoryDTO, Category, Long> implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
        this.categoryRepository = (CategoryRepository) super.repository;
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

}