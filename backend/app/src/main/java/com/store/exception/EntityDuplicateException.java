package com.store.exception;

public class EntityDuplicateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityDuplicateException(String message) {
        super(message);
    }
}