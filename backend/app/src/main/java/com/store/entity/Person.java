package com.store.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Person
 */
@MappedSuperclass
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(
        columnDefinition = "nvarchar(30)",
        nullable = false
    )
    private String lastname;

    @Column(
        columnDefinition = "nvarchar(30)",
        nullable = false
    )
    private String fistname;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(
        columnDefinition = "nvarchar(max)"
    )
    private String avartarPath;

    @Column(
        nullable = false
    )
    private String password;

    public Person(String lastname, String fistname, Date birthday, String avartarPath, String password) {
        this.lastname = lastname;
        this.fistname = fistname;
        this.birthday = birthday;
        this.avartarPath = avartarPath;
        this.password = password;
    }

    public Person(){
        
    }
}