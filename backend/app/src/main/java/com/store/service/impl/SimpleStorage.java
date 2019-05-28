package com.store.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.store.service.Storage;

import org.springframework.stereotype.Service;

/**
 * SimpleStorage
 */
@Service
public class SimpleStorage implements Storage {

    @Override
    public File storageFile(InputStream input, Path path) throws IOException {
        Files.copy(input, path, StandardCopyOption.REPLACE_EXISTING);
        return new File(path.toUri());
    }
    
}