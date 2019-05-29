package com.store.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Invoice
 */
@Entity
@Table(
    name = "invoice"
)
@Getter
@Setter
@NoArgsConstructor
public class Invoice {

    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(
        mappedBy = "invoice",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    private Set<DetailedInvoice> detailedInvoices;

}