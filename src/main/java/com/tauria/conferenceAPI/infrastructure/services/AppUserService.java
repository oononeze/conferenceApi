package com.tauria.conferenceAPI.infrastructure.services;

import com.tauria.conferenceAPI.models.commands.user.CreateUserCommand;
import com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection;
import com.tauria.conferenceAPI.models.projections.RoomParticipationProjection;
import com.tauria.conferenceAPI.models.projections.RoomUsersProjection;
import com.tauria.conferenceAPI.models.projections.TeamProjection;

public interface AppUserService {
    Iterable<RoomUsersProjection> getAllUsers();
    RoomUsersProjection getUserByUserName(String userName);
    Iterable<ConferenceRoomProjection> getAllCreatedRooms(String userName);
    Iterable<ConferenceRoomProjection> getAllRooms(String userName);
    Iterable<TeamProjection> getAllTeams(String userName);
    Iterable<RoomParticipationProjection> getUserRoomParticipationSet(String userName);
}
