package com.tauria.conferenceAPI.infrastructure.exception;

public class ItemNotSavedException extends RuntimeException {

    public ItemNotSavedException(String message){
        super(message);
    }
}
