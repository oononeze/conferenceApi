package com.tauria.conferenceAPI.appliation.stdServiceImpl;

import com.tauria.conferenceAPI.infrastructure.exception.ErrorMessage;
import com.tauria.conferenceAPI.infrastructure.exception.ItemNotFoundException;
import com.tauria.conferenceAPI.infrastructure.repositories.AppUserRepository;
import com.tauria.conferenceAPI.infrastructure.services.AppUserService;
import com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection;
import com.tauria.conferenceAPI.models.projections.RoomParticipationProjection;
import com.tauria.conferenceAPI.models.projections.RoomUsersProjection;
import com.tauria.conferenceAPI.models.projections.TeamProjection;

import java.util.ArrayList;
import java.util.List;
public class ApplicationUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;

    public ApplicationUserServiceImpl(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Iterable<RoomUsersProjection> getAllUsers() {

        var result = appUserRepository.findAll();
        List<RoomUsersProjection> usersProjections = new ArrayList<>();
        result.forEach(user -> {
            RoomUsersProjection projection = new RoomUsersProjection(user.getUserName(),
                    user.getFirstName(),user.getLastName());

            usersProjections.add(projection);
        });

        return usersProjections;
    }

    @Override
    public RoomUsersProjection getUserByUserName(String userName) {
        var result = appUserRepository.findById(userName);

        if(result.isPresent()){
            var user = result.get();
            var projection = new RoomUsersProjection(user.getUserName(),
                    user.getFirstName(),user.getLastName());
            return projection;
        }


        return null;
    }

    @Override
    public Iterable<ConferenceRoomProjection> getAllCreatedRooms(String userName) {

        var result = appUserRepository.findById(userName);

        if(result.isPresent()){
            var user = result.get();
            ArrayList<ConferenceRoomProjection> roomProjections = new ArrayList<>();

            user.getCreatedRooms().forEach(room -> {

                ConferenceRoomProjection projection = new ConferenceRoomProjection(room.getId(),
                        room.getName(),room.isAllowGuests(),room.getOwner(),room.getTeam());

                roomProjections.add(projection);
            });

            return roomProjections;
        }

        return null;
    }

    @Override
    public Iterable<ConferenceRoomProjection> getAllRooms(String userName) {

        var result = appUserRepository.findById(userName);

        if(result.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND);

            var user = result.get();
            ArrayList<ConferenceRoomProjection> roomProjections = new ArrayList<>();

            var conferenceRooms = user.getRoomParticipationSet()
                    .stream().distinct();

            conferenceRooms.forEach(participation -> {

                ConferenceRoomProjection projection = new ConferenceRoomProjection(participation.getConferenceRoom().getId(),
                        participation.getConferenceRoom().getName(),participation.getConferenceRoom().isAllowGuests(),
                        participation.getUser(),participation.getConferenceRoom().getTeam());

                roomProjections.add(projection);
            });

            return roomProjections;
    }

    @Override
    public Iterable<TeamProjection> getAllTeams(String userName) {
        var result = appUserRepository.findById(userName);

        if(result.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND);

        var user = result.get();


        List<TeamProjection> projections = new ArrayList<>();

        user.getTeams().stream().forEach(x -> {


            var teamProjection = new TeamProjection( x.getId(),
                    x.getName(), x.getConsumedConferenceTime(),
                    x.isHasExhaustedConferenceTime());
            projections.add(teamProjection);
        });

        return projections;
    }

    @Override
    public Iterable<RoomParticipationProjection> getUserRoomParticipationSet(String userName) {

        var result = appUserRepository.findById(userName);

        if(result.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND);

        var user = result.get();

        List<RoomParticipationProjection> participationProjections = new ArrayList<>();

       user.getRoomParticipationSet().stream().forEach(x -> {

            var userProjection = new RoomUsersProjection(x.getUser().getUserName(),
                    x.getUser().getFirstName(), x.getUser().getLastName());

            ConferenceRoomProjection conferenceRoomProjection = new ConferenceRoomProjection( x.getConferenceRoom().getId(),
                    x.getConferenceRoom().getName(), x.getConferenceRoom().isAllowGuests(),
                    x.getUser(),x.getConferenceRoom().getTeam());

            var projection = new RoomParticipationProjection(userProjection,conferenceRoomProjection,
                    x.isGuest(),x.isRequired(),x.getTimeJoined(),x.getTimeLeft(),x.getGuestEmail());

            participationProjections.add(projection);
        });

        return participationProjections;
    }
}
