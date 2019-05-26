package com.store.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDTO extends PersonDTO{

    private String username;

	public AdminDTO(String lastname, String firstname, Date birthday, String avartarPath, String password,
			String username) {
		super(lastname, firstname, birthday, avartarPath, password);
		this.username = username;
	}    
    
}