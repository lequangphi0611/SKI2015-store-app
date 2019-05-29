package com.store.entity;

import java.util.Date;
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
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer
 */
@Entity
@Table(
    name = "customer",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = { "phone_number", "email" })
    }
)
@Setter
@Getter
@NoArgsConstructor
public class Customer extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(
        nullable = false,
        columnDefinition = "nvarchar(255)"
    )
    private String address;

    @Column(
        nullable = false,
        length = 13,
        name = "phone_number"
    )
    private String phoneNumber;

    @OneToMany(
        mappedBy = "customer",
        fetch = FetchType.LAZY
    )
    private Set<Invoice> invoices;

	public Customer(String lastname, String fistname, Date birthday, String avartarPath, String password, long id,
			String email, String address, String phoneNumber, Set<Invoice> invoices) {
		super(lastname, fistname, birthday, avartarPath, password);
		this.id = id;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.invoices = invoices;
	}

    
}