package com.store.service;

import com.store.model.CategoryDTO;

/**
 * CategoryService
 */
public interface CategoryService extends CrudService<CategoryDTO, Long> {

    
    boolean existsByName(String name);


}