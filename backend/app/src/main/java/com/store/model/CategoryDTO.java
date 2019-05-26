package com.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CategoryDTO
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    private long id;

    private String name;

    public CategoryDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }
    

}