package com.store.service;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * StorageProperty
 */

@ConfigurationProperties("storage")
public class StorageProperties {

    private final String uploadFolderPath;

    public static final String RELATIVE_PATH = "/image";

    public StorageProperties() {
        this.uploadFolderPath = new StringBuffer(System.getProperty("user.dir"))
                .append("/src/main/resources/static").append(RELATIVE_PATH).toString();
        File uploadFolder = new File(uploadFolderPath);
        if (!uploadFolder.mkdirs())
            uploadFolder.mkdir();
    }

    public String getUploadFolderPath() {
        return this.uploadFolderPath;
    }

}