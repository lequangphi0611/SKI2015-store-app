package com.store.service.impl;

import com.store.entity.Admin;
import com.store.entity.Person;
import com.store.model.AdminDTO;
import com.store.model.PersonDTO;
import com.store.service.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMapper implements Mapper<AdminDTO,  Admin> {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public AdminDTO mapDto(Admin entity) {
        AdminDTO admin = (AdminDTO) personMapper.mapDto(entity);
        admin.setUsername(entity.getUsername());
        return admin;
    }

    @Override
    public Admin mapEntity(AdminDTO dto) {
        Admin admin = (Admin) personMapper.mapEntity(dto);
        admin.setUsername(dto.getUsername());
        return admin;
    }

    
}