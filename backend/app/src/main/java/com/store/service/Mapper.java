package com.store.service;

/**
 * 
 * @param <D> is DTO
 * @param <E> is Entity
 */
public interface Mapper<D, E> {

    D mapDto(E entity);

    E mapEntity(D dto);
    
}