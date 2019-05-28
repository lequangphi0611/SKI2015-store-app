package com.store.service.impl;

import com.store.entity.Category;
import com.store.entity.Product;
import com.store.model.CategoryDTO;
import com.store.model.ProductDTO;
import com.store.service.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductMapper
 */
@Service
public class ProductMapper implements Mapper<ProductDTO, Product> {

    @Autowired
    private Mapper<CategoryDTO, Category> categoryMapper;

    @Override
    public ProductDTO mapDto(Product entity) {
        ProductDTO product = new ProductDTO();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setManufactureDate(entity.getManufactureDate());
        product.setImagePath(entity.getImagePath());
        product.setPrice(entity.getPrice());
        product.setDescription(entity.getDescription());
        product.setCategory(categoryMapper.mapDto(entity.getCategory()));
        return product;
    }

    @Override
    public Product mapEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setManufactureDate(dto.getManufactureDate());
        product.setImagePath(dto.getImagePath());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setCategory(categoryMapper.mapEntity(dto.getCategory()));
        return product;
    }

}