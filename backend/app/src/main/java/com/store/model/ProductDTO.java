package com.store.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ProductDTO
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private long id;

    private String name;

    private Date manufactureDate;

    private String imagePath;

    private long price;

    private String description;

    private CategoryDTO category;

    public ProductDTO(long id, String name, Date manufactureDate, String imagePath, long price, String description,
            CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.imagePath = imagePath;
        this.price = price;
        this.description = description;
        this.category = category;
    }

}