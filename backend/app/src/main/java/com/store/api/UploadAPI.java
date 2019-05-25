package com.store.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import com.store.service.Storage;
import com.store.service.StorageProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * UploadAPI
 */
@RestController
@RequestMapping("/api")
public class UploadAPI {

    @Autowired
    private StorageProperties storageProperties;

    @Autowired
    private Storage storage;

    @PostMapping(value = "/storage/upload")
    public ResponseEntity<File> upload(@RequestBody MultipartFile file) throws IOException {

        final String path = storageProperties.getUploadFolderPath() + File.separator + file.getOriginalFilename();
        return new ResponseEntity<>(storage.storageFile(file.getInputStream(), Paths.get(path)), HttpStatus.OK);
    }

}