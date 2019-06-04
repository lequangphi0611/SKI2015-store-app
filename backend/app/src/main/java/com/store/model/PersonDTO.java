package com.store.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonDTO {

    private long id;

    private String lastname;

    private String firstname;

    private Date birthday;

    private String avartarPath;

    private String password;

    public PersonDTO(String lastname, String firstname, Date birthday, String avartarPath, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;
        this.avartarPath = avartarPath;
        this.password = password;
    }
    
}