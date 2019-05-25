package com.store.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DetailedInvoice
 */
@Entity
@Table(
    name = "detailed_invoice"
)
@Getter
@Setter
@NoArgsConstructor
public class DetailedInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private long price;

    @ColumnDefault(value = "1")
    private int quantity;
    
}