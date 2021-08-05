package com.tauria.conferenceAPI.appliation.stdServiceImpl;

import com.tauria.conferenceAPI.infrastructure.exception.ErrorMessage;
import com.tauria.conferenceAPI.infrastructure.exception.ItemAlreadyExistsException;
import com.tauria.conferenceAPI.infrastructure.exception.ItemNotFoundException;
import com.tauria.conferenceAPI.infrastructure.exception.ItemNotSavedException;
import com.tauria.conferenceAPI.infrastructure.repositories.AppUserRepository;
import com.tauria.conferenceAPI.infrastructure.repositories.ConferenceRoomRepository;
import com.tauria.conferenceAPI.infrastructure.repositories.TeamRepository;
import com.tauria.conferenceAPI.infrastructure.services.TeamService;
import com.tauria.conferenceAPI.models.applicationEntities.Team;
import com.tauria.conferenceAPI.models.commands.team.AddUserToTeamCommand;
import com.tauria.conferenceAPI.models.commands.team.CreateTeamCommand;
import com.tauria.conferenceAPI.models.commands.team.RemoveUserFromTeamCommand;
import com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection;
import com.tauria.conferenceAPI.models.projections.RoomUsersProjection;
import com.tauria.conferenceAPI.models.projections.TeamProjection;

import java.util.ArrayList;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private AppUserRepository appUserRepository;

    public TeamServiceImpl(TeamRepository teamRepository,
                           AppUserRepository appUserRepository){

        this.appUserRepository = appUserRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Boolean createTeam(CreateTeamCommand command) {
       var existingTeam  = teamRepository.findByTeamName(command.getName());
       if(existingTeam.isPresent()){
           var teams = (ArrayList) existingTeam.get();
           if(teams.size() > 0)
               throw new ItemAlreadyExistsException(ErrorMessage.TEAM_ALREADY_EXISTS);

           var userResult = appUserRepository.findById(command.getUserName());

           if(userResult.isEmpty())
               throw new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND);

           var user = userResult.get();

           var newTeam = new Team(command.getName());
           newTeam.addUser(user);

           var savedTeam = teamRepository.save(newTeam);

           if(savedTeam == null)
               throw new ItemNotSavedException(ErrorMessage.TEAM_NOT_SAVED);

           return true;
       }

       return false;
    }

    @Override
    public Boolean addUserToTeam(AddUserToTeamCommand command) {

        var teamResult = teamRepository.findById(command.getTeamId());

        if(teamResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.TEAM_NOT_FOUND);

        var userResult = appUserRepository.findById(command.getUserName());

        if(userResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND);

        var team = teamResult.get();
        var user = userResult.get();
        var userAdded = team.addUser(user);

        if(!userAdded)
            return false;

        teamRepository.save(team);

        return true;

    }

    @Override
    public Boolean removeUserFromTeam(RemoveUserFromTeamCommand command) {
      return true;
      //todo implement removing user from team.
    }

    @Override
    public TeamProjection getTeamById(long id) {
        var teamResult = teamRepository.findById(id);

        if(teamResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.TEAM_NOT_FOUND);

        var team = teamResult.get();

        return new TeamProjection(team.getId(),team.getName(),team.getConsumedConferenceTime(),
                team.isHasExhaustedConferenceTime());

    }

    @Override
    public Iterable<TeamProjection> getAllTeams() {
        var result = teamRepository.findAll();
        List<TeamProjection> teamProjections = new ArrayList<>();

        result.forEach(team -> {
            TeamProjection projection = new TeamProjection(team.getId(),team.getName(),
                    team.getConsumedConferenceTime(),
                    team.isHasExhaustedConferenceTime());

            teamProjections.add(projection);
        });

        return teamProjections;
    }

    @Override
    public Iterable<RoomUsersProjection> getAllTeamUsers(long id) {

        var teamResult = teamRepository.findById(id);

        if(teamResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.TEAM_NOT_FOUND);

        var team = teamResult.get();

        List<RoomUsersProjection> usersProjections = new ArrayList<>();

        team.getUsers().forEach(user -> {
            RoomUsersProjection projection = new RoomUsersProjection(user.getUserName(),
                    user.getFirstName(),user.getLastName());

            usersProjections.add(projection);
        });

        return usersProjections;
    }

    @Override
    public Iterable<ConferenceRoomProjection> getAllTeamRooms(long id) {
        var teamResult = teamRepository.findById(id);

        if(teamResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.TEAM_NOT_FOUND);

        var team = teamResult.get();
        List<ConferenceRoomProjection> roomProjections = new ArrayList<>();

        team.getConferences().forEach(room -> {
            ConferenceRoomProjection projection = new ConferenceRoomProjection(room.getId(),
                    room.getName(),room.isAllowGuests(),room.getOwner(),room.getTeam());

            roomProjections.add(projection);
        });

        return roomProjections;
    }
}
