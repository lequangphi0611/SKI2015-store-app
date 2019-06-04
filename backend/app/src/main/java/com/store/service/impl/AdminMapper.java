package com.store.service.impl;

import com.store.entity.Admin;
import com.store.model.AdminDTO;
import com.store.service.Mapper;

import org.springframework.stereotype.Service;

@Service
public class AdminMapper implements Mapper<AdminDTO, Admin> {

    @Override
    public AdminDTO mapDto(Admin entity) {
        AdminDTO admin = new AdminDTO();
        admin.setLastname(entity.getLastname());
        admin.setFirstname(entity.getFistname());
        admin.setAvartarPath(entity.getAvartarPath());
        admin.setBirthday(entity.getBirthday());
        admin.setPassword(entity.getPassword());
        admin.setUsername(entity.getUsername());
        admin.setId(entity.getId());
        return admin;
    }

    @Override
    public Admin mapEntity(AdminDTO dto) {
        Admin admin = new Admin();
        admin.setLastname(dto.getLastname());
        admin.setFistname(dto.getFirstname());
        admin.setAvartarPath(dto.getAvartarPath());
        admin.setBirthday(dto.getBirthday());
        admin.setPassword(dto.getPassword());
        admin.setUsername(dto.getUsername());
        admin.setId(dto.getId());
        return admin;
    }

}