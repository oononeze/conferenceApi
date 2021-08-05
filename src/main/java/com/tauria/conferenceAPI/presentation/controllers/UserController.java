package com.tauria.conferenceAPI.presentation.controllers;

import com.tauria.conferenceAPI.infrastructure.services.AppUserService;
import com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection;
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


@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private AppUserService userService;

    @GetMapping(path = "/",produces = "application/json")
    public ResponseEntity<Iterable<RoomUsersProjection>> getUsers(){
        try{
            return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "user_name")
    public ResponseEntity<RoomUsersProjection> getUserByUserName(@AuthenticationPrincipal OidcUser user){
        try{
            var userName = user.getUserInfo().getEmail();
            return new ResponseEntity<>(userService.getUserByUserName(userName),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "rooms",produces = "application/json")
    public ResponseEntity<Iterable<ConferenceRoomProjection>> getAllRooms(@AuthenticationPrincipal OidcUser user){
        try{
            var userName = user.getUserInfo().getEmail();
            return new ResponseEntity<>(userService.getAllRooms(userName),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "created_rooms",produces = "application/json")
    public ResponseEntity<Iterable<ConferenceRoomProjection>> getCreatedRooms(@AuthenticationPrincipal OidcUser user){
        try{
            var userName = user.getUserInfo().getEmail();
            return new ResponseEntity<>(userService.getAllCreatedRooms(userName),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "teams",produces = "application/json")
    public ResponseEntity<Iterable<TeamProjection>> getUserTeams(@AuthenticationPrincipal OidcUser user){
        try{
            var userName = user.getUserInfo().getEmail();
            return new ResponseEntity<>(userService.getAllTeams(userName),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }


}
