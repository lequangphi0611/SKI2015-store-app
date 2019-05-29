package com.store.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.store.service.CrudService;
import com.store.service.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public class AbstracCrudService<D, E, ID> implements CrudService<D, ID> {

    protected final JpaRepository<E, ID> repository;

    @Autowired
    protected Mapper<D, E> mapper;

    @Autowired
    public AbstracCrudService(JpaRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    public Collection<D> findAll() {
        return repository.findAll().stream().map(mapper::mapDto).collect(Collectors.toList());
    }

    @Override
    public D getOneById(ID id) {
        return mapper.mapDto(repository.getOne(id));
    }

    @Override
    public D save(D dto) {
        return mapper.mapDto(repository.save(mapper.mapEntity(dto)));
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Collection<D> saveAll(Collection<D> dtos) {
        Collection<E> entities = dtos
                .stream()
                .map(mapper::mapEntity)
                .collect(Collectors
                .toList());
        return repository.saveAll(entities)
                .stream()
                .map(mapper::mapDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByID(ID id) {
        return repository.existsById(id);
    }

}