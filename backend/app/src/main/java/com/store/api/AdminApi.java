package com.store.api;

import java.util.Collection;

import com.store.entity.Admin;
import com.store.exception.EntityDuplicateException;
import com.store.exception.EntityNotFoundException;
import com.store.model.AdminDTO;
import com.store.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
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
        return adminService.findeByUsername(username);
    }

    @PostMapping
    public AdminDTO create(@RequestBody AdminDTO admin) {
        
        if(adminService.existsByUsername(admin.getUsername())) {
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

    @PutMapping("/{id}")
    public AdminDTO update(@PathVariable long id, @RequestBody AdminDTO admin) {

        if(!adminService.existsByID(id)) {
            throw new EntityNotFoundException(
                new StringBuilder(Admin.class.getName())
                    .append(" width id '")
                    .append(id)
                    .append("' does not exists !")
                    .toString()
            );
        }

        admin.setId(id);
        return adminService.save(admin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        adminService.deleteById(id);
    }
     
}