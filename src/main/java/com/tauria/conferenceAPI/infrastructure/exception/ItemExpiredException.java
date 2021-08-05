package com.tauria.conferenceAPI.infrastructure.exception;

public class ItemExpiredException extends RuntimeException {

    public ItemExpiredException(String message) {
        super(message);
    }
}
