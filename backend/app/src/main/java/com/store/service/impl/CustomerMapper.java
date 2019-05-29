package com.store.service.impl;

import com.store.entity.Customer;
import com.store.model.CustomerDTO;
import com.store.service.Mapper;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper implements Mapper<CustomerDTO, Customer> {

    @Override
    public CustomerDTO mapDto(Customer entity) {
        CustomerDTO customer = new CustomerDTO();
        customer.setLastname(entity.getLastname());
        customer.setFirstname(entity.getFistname());
        customer.setAvartarPath(entity.getAvartarPath());
        customer.setBirthday(entity.getBirthday());
        customer.setPassword(entity.getPassword());
        customer.setId(entity.getId());
        customer.setAddress(entity.getAddress());
        customer.setPhoneNumber(entity.getPhoneNumber());
        customer.setEmail(entity.getEmail());
        return customer;
    }

    @Override
    public Customer mapEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setLastname(dto.getLastname());
        customer.setFistname(dto.getFirstname());
        customer.setAvartarPath(dto.getAvartarPath());
        customer.setBirthday(dto.getBirthday());
        customer.setPassword(dto.getPassword());
        customer.setId(dto.getId());
        customer.setAddress(dto.getAddress());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setEmail(dto.getEmail());
        return customer;
    }

    
}