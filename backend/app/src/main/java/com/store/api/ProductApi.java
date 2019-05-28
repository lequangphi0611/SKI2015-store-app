package com.store.api;

import java.util.Collection;

import com.store.entity.Product;
import com.store.exception.EntityDuplicateException;
import com.store.exception.EntityNotFoundException;
import com.store.model.ProductDTO;
import com.store.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductApi {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Collection<ProductDTO> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable long id) {
        return productService.getOneById(id);
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO product) {

        if(productService.existsByName(product.getName())) {
            throw new EntityDuplicateException(
                new StringBuilder(Product.class.getName())
                    .append(" with name = '")
                    .append(product.getName())
                    .append("' is already exists !")
                    .toString()
            );
        }

        return productService.save(product);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable long id ,@RequestBody ProductDTO product) {

        if(!productService.existsByID(id)){
            throw new EntityNotFoundException(
                new StringBuilder(Product.class.getName())
                    .append(" with id = '")
                    .append(product.getId())
                    .append("' does not exists !")
                    .toString()
                );
        }
        
        final ProductDTO oldProduct = productService.getOneById(id);
        if(oldProduct.getName().equals(product.getName())) {
            return oldProduct;
        } else if(productService.existsByName(product.getName())) {
            throw new EntityNotFoundException(
                new StringBuilder(Product.class.getName())
                    .append(" with name = '")
                    .append(product.getName())
                    .append("' is already exists !")
                    .toString()
                );
        }

        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        productService.deleteById(id);
    }
    
}