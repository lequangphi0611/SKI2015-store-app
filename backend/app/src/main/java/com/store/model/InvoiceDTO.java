package com.store.model;

import java.util.Date;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InvoiceDTO {

    private String id;

    private Date paymentDate;

    private CustomerDTO customer;

    public InvoiceDTO(String id, Date paymentDate, CustomerDTO customer) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.customer = customer;
    }

}