package com.store.service.impl;

import javax.transaction.Transactional;

import com.store.entity.Admin;
import com.store.model.AdminDTO;
import com.store.respository.AdminRepository;
import com.store.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminServiceImpl extends AbstracCrudService<AdminDTO, Admin, Long> implements AdminService {

    @Autowired
    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

    @Override
    public boolean existsByUsername(String username) {
        return ((AdminRepository)repository).existsByUsername(username);
    }

    @Override
    public AdminDTO findeByUsername(String username) {
        return mapper.mapDto(((AdminRepository)repository).findByUsername(username));
    }

}