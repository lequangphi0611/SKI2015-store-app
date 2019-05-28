package com.store.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import com.store.exception.FileContentTypeNotAcceptedException;
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

    static final String[] ACCEPT_IMAGE_TYPE = { "IMAGE/PNG", "IMAGE/JPEG" };

    public boolean matchAcceptImageType(String type) {
        for (String imageType : ACCEPT_IMAGE_TYPE) {
            if (imageType.equalsIgnoreCase(type))
                return true;
        }
        return false;
    }

    public String getFilenameFrom(MultipartFile file, String... filenames) {
        boolean isPresentFilename = filenames != null && filenames.length > 0 && filenames[0] != null
                && !filenames[0].isEmpty();

        String originFilename = file.getOriginalFilename();
        if (!isPresentFilename)
            return originFilename;

        String originFileTail = originFilename.substring(originFilename.indexOf("."));
        return new StringBuilder(filenames[0]).append(originFileTail).toString();
    }

    @PostMapping(value = "/storage/upload")
    public ResponseEntity<File> upload(@RequestBody MultipartFile file, @RequestParam(required = false) String fileName)
            throws IOException, FileContentTypeNotAcceptedException {

        if (matchAcceptImageType(file.getContentType())) {
            String filename = getFilenameFrom(file, fileName);
            String path = storageProperties.getUploadFolderPath() + File.separator + filename;
            return new ResponseEntity<>(storage.storageFile(file.getInputStream(), Paths.get(path)), HttpStatus.OK);
        }

        StringBuilder builderMessage = new StringBuilder("File with Content-type = '");
        builderMessage.append(file.getContentType()).append("' is not accepted !. File must have Content-type = '")
                .append(String.join(", ", ACCEPT_IMAGE_TYPE));
        throw new FileContentTypeNotAcceptedException(builderMessage.toString());
    }

}