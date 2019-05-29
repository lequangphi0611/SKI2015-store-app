package com.store.api;

import java.util.Collection;

import com.store.entity.Admin;
import com.store.exception.EntityDuplicateException;
import com.store.exception.EntityNotFoundException;
import com.store.model.AdminDTO;
import com.store.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins")
public class AdminApi {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public Collection<AdminDTO> getAdmins() {
        return adminService.findAll();
    }

    @GetMapping("/{username}")
    public AdminDTO getAdmin(@PathVariable String username) {
        return adminService.getOneById(username);
    }

    @PostMapping
    public AdminDTO create(@RequestBody AdminDTO admin) {
        
        if(adminService.existsByID(admin.getUsername())) {
            throw new EntityDuplicateException(
                new StringBuilder(Admin.class.getName())
                    .append(" with username = '")
                    .append(admin.getUsername())
                    .append("' is already exists !")
                    .toString()
            );
        }

        return adminService.save(admin);
    }

    @PutMapping("/{username}")
    public AdminDTO update(@PathVariable String username, @RequestBody AdminDTO admin) {

        if(!adminService.existsByID(username)) {
            throw new EntityNotFoundException(
                new StringBuilder(Admin.class.getName())
                    .append(" width username '")
                    .append(username)
                    .append("' does not exists !")
                    .toString()
            );
        }

        return adminService.save(admin);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        adminService.deleteById(username);
    }
     
}