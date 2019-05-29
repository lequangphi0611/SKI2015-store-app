package com.store.service;

import com.store.model.CustomerDTO;

/**
 * CustomerService
 */
public interface CustomerService extends CrudService<CustomerDTO, Long> {

    boolean existsByEmail(String email);

    CustomerDTO findByEmail(String email);
    
}