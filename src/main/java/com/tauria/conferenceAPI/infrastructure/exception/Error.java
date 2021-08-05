package com.tauria.conferenceAPI.infrastructure.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Error {
    private int errorCode;
    private String error;
    private String errorMessage;
    private List<String> fieldErrors;

    Error(HttpStatus status, String message, List<String> fieldErrors) {
        this.errorCode = status.value();
        this.error = status.name();
        this.errorMessage = message;
        this.fieldErrors = fieldErrors;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}

