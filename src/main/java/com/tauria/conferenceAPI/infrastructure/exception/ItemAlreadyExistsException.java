package com.tauria.conferenceAPI.infrastructure.exception;


public class ItemAlreadyExistsException extends RuntimeException {

    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
