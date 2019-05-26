package com.store.service.impl;

import com.store.entity.Product;
import com.store.model.ProductDTO;
import com.store.respository.ProductRepository;
import com.store.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends AbstracCrudService<ProductDTO, Product, Long> implements ProductService {

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
    }
    
}