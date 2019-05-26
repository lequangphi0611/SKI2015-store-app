package com.store.service;

import java.util.Collection;

/**
 * CrudService
 */
public interface CrudService<D, ID> {

    Collection<D> findAll();

    D getOneById(ID id);

    D save(D d);

    void deleteById(ID id);
    
}