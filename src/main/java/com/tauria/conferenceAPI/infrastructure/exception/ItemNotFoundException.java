package com.tauria.conferenceAPI.infrastructure.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message){
        super(message);
    }

}
