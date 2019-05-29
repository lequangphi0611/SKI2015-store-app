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
public class AdminServiceImpl extends AbstracCrudService<AdminDTO, Admin, String> implements AdminService {

    @Autowired
    public AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }

}