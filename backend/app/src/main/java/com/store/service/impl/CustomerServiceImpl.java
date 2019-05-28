package com.store.service.impl;

import com.store.entity.Customer;
import com.store.model.CustomerDTO;
import com.store.respository.CustomerRepository;
import com.store.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends AbstracCrudService<CustomerDTO, Customer, Long> implements CustomerService {

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
    }

    
}