package com.store.service;

import java.util.Collection;

import com.store.model.ProductDTO;

public interface ProductService extends CrudService<ProductDTO, Long> {

    boolean existsByName(String name);

    Collection<ProductDTO> findAllByCategoryId(long id);
    
}