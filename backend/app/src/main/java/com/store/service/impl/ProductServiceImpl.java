package com.store.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.store.entity.Product;
import com.store.model.ProductDTO;
import com.store.respository.ProductRepository;
import com.store.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductServiceImpl extends AbstracCrudService<ProductDTO, Product, Long> implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        super(repository);
        this.productRepository = (ProductRepository) super.repository;
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }
    
    @Override
    public Collection<ProductDTO> findAllByCategoryId(long id) {
        return productRepository.findAllByCategoryId(id)
                    .stream()
                    .map(mapper::mapDto)
                    .collect(Collectors.toList());
    }

}