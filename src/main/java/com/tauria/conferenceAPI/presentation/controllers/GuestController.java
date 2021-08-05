package com.tauria.conferenceAPI.presentation.controllers;

import com.tauria.conferenceAPI.infrastructure.services.ConferenceRoomService;
import com.tauria.conferenceAPI.models.commands.conference.JoinConferenceAsGuestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/guest")
@Validated
public class GuestController {

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @PostMapping(path = "join_conference", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> joinAsGuest(@Valid @RequestBody JoinConferenceAsGuestCommand command){
        try{
                return new ResponseEntity<>(conferenceRoomService.joinConferenceAsGuest(command), HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }
}
