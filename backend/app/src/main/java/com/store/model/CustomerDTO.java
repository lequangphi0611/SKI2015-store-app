package com.store.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO extends PersonDTO {

    private long id;

    private String email;

    private String address;

    private String phoneNumber;

	public CustomerDTO(String lastname, String firstname, Date birthday, String avartarPath, String password, long id,
			String email, String address, String phoneNumber) {
		super(lastname, firstname, birthday, avartarPath, password);
		this.id = id;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
    
}