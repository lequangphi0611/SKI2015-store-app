package com.store.api;

import java.util.Collection;

import com.store.entity.Category;
import com.store.exception.EntityDuplicateException;
import com.store.exception.EntityNotFoundException;
import com.store.model.CategoryDTO;
import com.store.model.ProductDTO;
import com.store.service.CategoryService;
import com.store.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryAPI {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Collection<CategoryDTO>> getCategories() {
        return new ResponseEntity<Collection<CategoryDTO>>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategory(@PathVariable long id) {
        return categoryService.getOneById(id);
    }

    @GetMapping("/{id}/products")
    public Collection<ProductDTO> getProductByCategoryId(@PathVariable long id, @RequestParam(name = "q", required = false) String query) {
        if(query != null && !query.isEmpty()) {
            return productService.findAllByCategoryIdAndNameContaining(id, query);
        }
        return productService.findAllByCategoryId(id);
    }

    @PostMapping
    public CategoryDTO insert(@RequestBody CategoryDTO category) {
        CategoryDTO newCategory = new CategoryDTO();
        newCategory.setName(category.getName());
        
        if(categoryService.existsByName(newCategory.getName())) {
            throw new EntityDuplicateException(
                new StringBuilder(Category.class.getName())
                    .append(" with name = '")
                    .append(newCategory.getName())
                    .append("' is already exists!")
                    .toString()
            );
        }

        return categoryService.save(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable long id, @RequestBody CategoryDTO category) {

        if(!categoryService.existsByID(id)) {
            throw new EntityNotFoundException(
                new StringBuilder(Category.class.getName())
                    .append(" width id = '")
                    .append(id)
                    .append("' does not exists !")
                    .toString()
            );
        }

        CategoryDTO oldCategory = categoryService.getOneById(id);
        if(oldCategory.getName().equals(category.getName())) {
            return new ResponseEntity<CategoryDTO>(oldCategory, HttpStatus.OK);
        } else if (categoryService.existsByName(category.getName())) {
            throw new EntityDuplicateException(
                new StringBuilder(Category.class.getName())
                    .append(" with name = '")
                    .append(category.getName())
                    .append("' is already exists!")
                    .toString()
            );
        }

        CategoryDTO categoryUpdated = categoryService.save(new CategoryDTO(id, category.getName()));
        return new ResponseEntity<CategoryDTO>(categoryUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        categoryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}