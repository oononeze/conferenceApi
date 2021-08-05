package com.tauria.conferenceAPI.presentation.controllers;

import com.tauria.conferenceAPI.infrastructure.services.TeamService;
import com.tauria.conferenceAPI.models.commands.team.AddUserToTeamCommand;
import com.tauria.conferenceAPI.models.commands.team.CreateTeamCommand;
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

import javax.validation.Valid;

@RestController
@RequestMapping("/teams")
@Validated
public class TeamController {

    @Autowired
    private TeamService teamService;

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> createTeam(@Valid @RequestBody CreateTeamCommand command, @AuthenticationPrincipal OidcUser user){
        try{
             var userName = user.getUserInfo().getEmail();
             command.setUserName(userName);
            return new ResponseEntity<>(teamService.createTeam(command), HttpStatus.CREATED);
        }

        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }

    @PostMapping(path = "admin/add_user", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Boolean> addUserTeam(@Valid @RequestBody AddUserToTeamCommand command ){

        try{
            return new ResponseEntity<>(teamService.addUserToTeam(command), HttpStatus.CREATED);
        }

        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, ex.getMessage());
        }
    }


    @GetMapping(path = "/",produces = "application/json")
    public ResponseEntity<Iterable<TeamProjection>> getAllTeams(){
        try{
            return new ResponseEntity<>(teamService.getAllTeams(),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "/{id}",produces = "application/json")
    public ResponseEntity<TeamProjection> getTeamById(long id){
        try{
            return new ResponseEntity<>(teamService.getTeamById(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "rooms/{id}",produces = "application/json")
    public ResponseEntity<Iterable<ConferenceRoomProjection>> getAllRooms(long id){
        try{
            return new ResponseEntity<>(teamService.getAllTeamRooms(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }

    @GetMapping(path = "users/{id}",produces = "application/json")
    public ResponseEntity<Iterable<RoomUsersProjection>> getAllTeamUsers(long id){
        try{
            return new ResponseEntity<>(teamService.getAllTeamUsers(id),HttpStatus.OK);
        }
        catch(Exception ex){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,ex.getMessage());
        }

    }
}
