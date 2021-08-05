package com.tauria.conferenceAPI.infrastructure.services;

import com.tauria.conferenceAPI.models.applicationEntities.ConferenceRoom;
import com.tauria.conferenceAPI.models.commands.team.AddUserToTeamCommand;
import com.tauria.conferenceAPI.models.commands.team.CreateTeamCommand;
import com.tauria.conferenceAPI.models.commands.team.RemoveUserFromTeamCommand;
import com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection;
import com.tauria.conferenceAPI.models.projections.RoomUsersProjection;
import com.tauria.conferenceAPI.models.projections.TeamProjection;

public interface TeamService {

    Boolean createTeam(CreateTeamCommand command);
    Boolean addUserToTeam(AddUserToTeamCommand command);
    Boolean removeUserFromTeam(RemoveUserFromTeamCommand command);
    TeamProjection getTeamById(long id);
    Iterable<TeamProjection> getAllTeams();
    Iterable<RoomUsersProjection> getAllTeamUsers(long id);
    Iterable<ConferenceRoomProjection> getAllTeamRooms(long id);

}
