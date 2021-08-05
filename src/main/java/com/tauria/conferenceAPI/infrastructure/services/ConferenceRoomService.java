package com.tauria.conferenceAPI.infrastructure.services;

import com.tauria.conferenceAPI.models.commands.conference.AddUserToConferenceCommand;
import com.tauria.conferenceAPI.models.commands.conference.CreateRoomCommand;
import com.tauria.conferenceAPI.models.commands.conference.JoinConferenceAsGuestCommand;
import com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection;
import com.tauria.conferenceAPI.models.projections.RoomParticipationProjection;
import com.tauria.conferenceAPI.models.projections.RoomUsersProjection;
import com.tauria.conferenceAPI.models.projections.TeamProjection;


public interface ConferenceRoomService {

    Boolean createConferenceRoom(CreateRoomCommand createRoomComand);
    Iterable<ConferenceRoomProjection> getAll();
    ConferenceRoomProjection getById(long Id);
    Iterable<RoomUsersProjection> getAllUsers(long Id);
    Iterable<RoomUsersProjection> getAllOptionalUsers(long Id);
    Iterable<RoomUsersProjection> getAllRequiredUsers(long Id);
    RoomUsersProjection getOwner(long Id);
    TeamProjection getTeam(long Id);
    Boolean addUserToRoom(AddUserToConferenceCommand conferenceCommand);
    Iterable<RoomParticipationProjection> getRoomParticipationSet(long Id);
    Boolean joinConferenceAsGuest(JoinConferenceAsGuestCommand command);

}
