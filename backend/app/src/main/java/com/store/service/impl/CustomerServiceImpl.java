package com.store.service.impl;

import javax.transaction.Transactional;

import com.store.entity.Customer;
import com.store.model.CustomerDTO;
import com.store.respository.CustomerRepository;
import com.store.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerServiceImpl extends AbstracCrudService<CustomerDTO, Customer, Long> implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository);
        this.customerRepository = (CustomerRepository) super.repository;
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public CustomerDTO findByEmail(String email) {
        return mapper.mapDto(customerRepository.findByEmail(email));
    }

    
}