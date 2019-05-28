package com.store.service;

import java.util.Collection;

/**
 * CrudService
 */
public interface CrudService<D, ID> {

    Collection<D> findAll();

    D getOneById(ID id);

    D save(D dto);

    Collection<D> saveAll(Collection<D> dtos);

    void deleteById(ID id);

    boolean existsByID(ID id);
    
}