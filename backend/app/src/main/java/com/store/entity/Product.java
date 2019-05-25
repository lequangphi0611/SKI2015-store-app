package com.store.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Product
 */
@Entity
@Table(
    name = "product",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "name" })
    }
)
@Setter
@Getter
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = -2629938827895872066L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column (
        columnDefinition = "nvarchar(255)",
        nullable = false
    )
    private String name;

    @Temporal(TemporalType.DATE)
    private Date manufactureDate;

    @Column(columnDefinition = "nvarchar(max)")
    private String imagePath;

    private long price;

    @Column(columnDefinition = "nvarchar(max)")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    
}