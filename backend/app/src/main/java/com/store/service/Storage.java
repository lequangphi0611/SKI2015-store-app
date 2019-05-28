package com.store.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Storage
 */
public interface Storage {

    File storageFile(InputStream input, Path path) throws IOException;

}