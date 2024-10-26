package com.rakbank;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String eventNotFound) {
        super(eventNotFound);
    }
}
