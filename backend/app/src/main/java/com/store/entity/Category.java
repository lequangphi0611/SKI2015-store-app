package com.store.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

/**
 * Category
 */
@Entity
@Table(
    name = "category",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name" })
    }
)
@Setter
@Getter
public class Category {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column (
        columnDefinition = "nvarchar(50)",
        nullable = false
    )
    private String name;

    @OneToMany(
        mappedBy = "category",
        fetch = FetchType.LAZY
    )
    private Set<Product> products;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
        
    }
    
    
}