package com.tauria.conferenceAPI.presentation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping(path = "/liveness_probe",produces = "application/json")
    public ResponseEntity<String> isAlive(){
        try{
            return new ResponseEntity<>("I still work!", HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Error reaching service.");
        }
    }

    @PostMapping(path = "/readiness_probe",produces = "application/json")
    public ResponseEntity<Boolean> isReady(){
        try{
            //TODO Implement me!
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Error serviceNotReady");
        }
    }
}
