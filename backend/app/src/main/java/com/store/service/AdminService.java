package com.store.service;

import com.store.model.AdminDTO;

/**
 * AdminService
 */
public interface AdminService extends CrudService<AdminDTO, Long> {

    boolean existsByUsername(String username);
    
    AdminDTO findeByUsername(String username);
}