package com.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailedInvoiceDTO {

    private long id;

    private InvoiceDTO invoice;

    private ProductDTO product;

    private long price;

    private int quantity;

    public DetailedInvoiceDTO(long id, InvoiceDTO invoice, ProductDTO product, long price, int quantity) {
        this.id = id;
        this.invoice = invoice;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }
    
}