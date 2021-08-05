package com.tauria.conferenceAPI.presentation.controllers;

import com.tauria.conferenceAPI.infrastructure.services.ConferenceRoomService;
import com.tauria.conferenceAPI.models.commands.conference.AddUserToConferenceCommand;
import com.tauria.conferenceAPI.models.commands.conference.CreateRoomCommand;
import com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection;
import com.tauria.conferenceAPI.models.projections.RoomParticipationProjection;
import com.tauria.conferenceAPI.models.projections.RoomUsersProjection;
import com.tauria.conferenceAPI.models.projections.TeamProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/rooms")
@Validated
public class ConferenceController {

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> createRoom(@Valid @RequestBody CreateRoomCommand command , @AuthenticationPrincipal OidcUser user){
        try{
             var userName = user.getUserInfo().getEmail();
             command.setOwnerUserName(userName);
             return new ResponseEntity<>(conferenceRoomService.createConferenceRoom(command), HttpStatus.CREATED);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

    @PostMapping(path = "add_user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> addUserToRoom(@Valid @RequestBody AddUserToConferenceCommand command ){

        try{
            return new ResponseEntity<>(conferenceRoomService.addUserToRoom(command), HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

    @GetMapping(path = "/",produces = "application/json")
    public ResponseEntity<Iterable<ConferenceRoomProjection>> getAllTeamRooms(){
        try{
            return new ResponseEntity<>(conferenceRoomService.getAll(),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "/{id}",produces = "application/json")
    public ResponseEntity<ConferenceRoomProjection> getById(long id){
        try{
            return new ResponseEntity<>(conferenceRoomService.getById(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "users/{id}",produces = "application/json")
    public ResponseEntity<Iterable<RoomUsersProjection>> getAllUsers(long id){
        try{
            return new ResponseEntity<>(conferenceRoomService.getAllUsers(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "optional/{id}",produces = "application/json")
    public ResponseEntity<Iterable<RoomUsersProjection>> getAllOptionalUsers(long id){
        try{
            return new ResponseEntity<>(conferenceRoomService.getAllOptionalUsers(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "required/{id}",produces = "application/json")
    public ResponseEntity<Iterable<RoomUsersProjection>> getAllRequiredlUsers(long id){
        try{
            return new ResponseEntity<>(conferenceRoomService.getAllRequiredUsers(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "owner/{id}",produces = "application/json")
    public ResponseEntity<RoomUsersProjection> getOwner(long id){
        try{
            return new ResponseEntity<>(conferenceRoomService.getOwner(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "team/{id}",produces = "application/json")
    public ResponseEntity<TeamProjection> getTeam(long id){
        try{
            return new ResponseEntity<>(conferenceRoomService.getTeam(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "participation/{id}",produces = "application/json")
    public ResponseEntity<Iterable<RoomParticipationProjection>> getParticipationSet(long id){
        try{
            return new ResponseEntity<>(conferenceRoomService.getRoomParticipationSet(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }


}
