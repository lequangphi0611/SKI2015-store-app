package com.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileContentTypeNotAcceptedException extends Exception {

    private static final long serialVersionUID = -8204749078055441347L;


    public FileContentTypeNotAcceptedException() {
        super();
    }

    public FileContentTypeNotAcceptedException(String message) {
        super(message);
    }
    
    
}