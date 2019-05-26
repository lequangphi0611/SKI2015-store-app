package com.store.service.impl;

import com.store.entity.Person;
import com.store.model.PersonDTO;
import com.store.service.Mapper;

import org.springframework.stereotype.Service;

@Service
public class PersonMapper implements Mapper<PersonDTO, Person> {

    @Override
    public PersonDTO mapDto(Person entity) {
        PersonDTO person = new PersonDTO();
        person.setLastname(entity.getLastname());
        person.setBirthday(entity.getBirthday());
        person.setFirstname(entity.getFistname());
        person.setPassword(entity.getPassword());
        person.setAvartarPath(entity.getAvartarPath());
        return person;
    }

    @Override
    public Person mapEntity(PersonDTO dto) {
        Person person = new Person();
        person.setLastname(dto.getLastname());
        person.setBirthday(dto.getBirthday());
        person.setFistname(dto.getFirstname());
        person.setPassword(dto.getPassword());
        person.setAvartarPath(dto.getAvartarPath());
        return person;
    }

    
}