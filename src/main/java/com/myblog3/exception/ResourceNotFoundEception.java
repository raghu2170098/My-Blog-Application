package com.myblog3.exception;

public class ResourceNotFoundEception extends RuntimeException {
    public ResourceNotFoundEception(String message) {
        super(message);
    }
}
