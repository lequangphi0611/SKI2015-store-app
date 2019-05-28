package com.store.service.impl;

import com.store.entity.Customer;
import com.store.entity.Person;
import com.store.model.CustomerDTO;
import com.store.model.PersonDTO;
import com.store.service.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper implements Mapper<CustomerDTO, Customer> {

    @Autowired
    private Mapper<PersonDTO, Person> personMapper;

    @Override
    public CustomerDTO mapDto(Customer entity) {
        CustomerDTO customer = (CustomerDTO) personMapper.mapDto(entity);
        customer.setId(entity.getId());
        customer.setAddress(entity.getAddress());
        customer.setPhoneNumber(entity.getPhoneNumber());
        customer.setEmail(entity.getEmail());
        return customer;
    }

    @Override
    public Customer mapEntity(CustomerDTO dto) {
        Customer customer = (Customer) personMapper.mapEntity(dto);
        customer.setId(dto.getId());
        customer.setAddress(dto.getAddress());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setEmail(dto.getEmail());
        return customer;
    }

    
}