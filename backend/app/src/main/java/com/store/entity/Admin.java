package com.store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Admin
 */
@Entity
@Table(
    name = "admin"
)
@Getter
@Setter
@NoArgsConstructor
public class Admin extends Person {

	@Column(
		nullable = false,
		unique = true
	)
    private String username;

	public Admin(String lastname, String fistname, Date birthday, String avartarPath, String password,
			String username) {
		super(lastname, fistname, birthday, avartarPath, password);
		this.username = username;
	}

}