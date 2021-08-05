package com.tauria.conferenceAPI.appliation.stdServiceImpl;

import com.tauria.conferenceAPI.infrastructure.exception.*;
import com.tauria.conferenceAPI.infrastructure.repositories.*;
import com.tauria.conferenceAPI.infrastructure.services.ConferenceRoomService;
import com.tauria.conferenceAPI.infrastructure.util.UserTypes;
import com.tauria.conferenceAPI.models.applicationEntities.AppUser;
import com.tauria.conferenceAPI.models.applicationEntities.Authority;
import com.tauria.conferenceAPI.models.applicationEntities.ConferenceRoom;
import com.tauria.conferenceAPI.models.applicationEntities.RoomParticipation;
import com.tauria.conferenceAPI.models.commands.conference.AddUserToConferenceCommand;
import com.tauria.conferenceAPI.models.commands.conference.CreateRoomCommand;
import com.tauria.conferenceAPI.models.commands.conference.JoinConferenceAsGuestCommand;
import com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection;
import com.tauria.conferenceAPI.models.projections.RoomParticipationProjection;
import com.tauria.conferenceAPI.models.projections.RoomUsersProjection;
import com.tauria.conferenceAPI.models.projections.TeamProjection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConferenceRoomServiceImpl implements ConferenceRoomService {

    private TeamRepository teamRepository;
    private ConferenceRoomRepository conferenceRoomRepository;
    private AppUserRepository appUserRepository;
    private RoomParticipationRepository roomParticipationRepository;
    private AuthorityRepository authorityRepository;

    public ConferenceRoomServiceImpl(TeamRepository teamRepository,
                                     ConferenceRoomRepository conferenceRoomRepository,
                                     AppUserRepository appUserRepository,
                                     RoomParticipationRepository roomParticipationRepository,
                                     AuthorityRepository authorityRepository){
        this.appUserRepository = appUserRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.teamRepository = teamRepository;
        this.roomParticipationRepository = roomParticipationRepository;
        this.authorityRepository = authorityRepository;
    }


    @Override
    public Boolean createConferenceRoom(CreateRoomCommand createRoomCommand) {

       var teamResult = teamRepository.findById(createRoomCommand.getTeamId());

       if(teamResult.isEmpty())
           throw new ItemNotFoundException(ErrorMessage.TEAM_NOT_FOUND);

       var team = teamResult.get();

        var userResult = appUserRepository.findById(createRoomCommand.getOwnerUserName());

        if(userResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND);

        var user = userResult.get();

        var existingRoom = conferenceRoomRepository.findByName(createRoomCommand.getName());

        if(existingRoom.isPresent()) {
            var rooms = (ArrayList) existingRoom.get();
            if (rooms.size() > 0)
                throw new ItemAlreadyExistsException(ErrorMessage.CONFERENCE_ROOM_ALREADY_EXISTS);
        }

        var room = new ConferenceRoom(createRoomCommand.getName(),user,team,
                createRoomCommand.isAllowGuests());

        var savedRoom = conferenceRoomRepository.save(room);
        if(savedRoom == null)
            throw new ItemNotSavedException(ErrorMessage.CONFERENCE_ROOM_NOT_SAVED);

        var participation = new RoomParticipation(user,savedRoom,false,
                true,user.getUserName(), new Date().getTime());

        var savedParticipation = roomParticipationRepository.save(participation);

        if(savedParticipation == null)
            throw new ItemNotSavedException(ErrorMessage.PARTICIPATION_RECORD_NOT_SAVED);

        return true;

    }

    @Override
    public Iterable<ConferenceRoomProjection> getAll() {

        var conferenceRoomsResult = conferenceRoomRepository.findAll();


        List<ConferenceRoomProjection> roomProjections = new ArrayList<>();

        conferenceRoomsResult.forEach(room -> {
            ConferenceRoomProjection projection = new ConferenceRoomProjection(room.getId(),
                    room.getName(),room.isAllowGuests(),room.getOwner(),room.getTeam());

            roomProjections.add(projection);
        });

        return roomProjections;
    }

    @Override
    public ConferenceRoomProjection getById(long Id) {

        var roomResult = conferenceRoomRepository.findById(Id);
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var room = roomResult.get();

        ConferenceRoomProjection projection = new ConferenceRoomProjection(room.getId(),
                room.getName(),room.isAllowGuests(),room.getOwner(),room.getTeam());

        return projection;

    }

    @Override
    public Iterable<RoomUsersProjection> getAllUsers(long Id) {
        var roomResult = conferenceRoomRepository.findById(Id);
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var room = roomResult.get();
        List<RoomUsersProjection> users = new ArrayList<>();

        room.getRoomParticipationSet().stream().forEach(x -> {

            var userProjection = new RoomUsersProjection(x.getUser().getUserName(),
                    x.getUser().getFirstName(), x.getUser().getLastName());

            if (!users.contains(userProjection))
                users.add(userProjection);
        });

        return users;

    }

    @Override
    public Iterable<RoomUsersProjection> getAllOptionalUsers(long Id) {
        var roomResult = conferenceRoomRepository.findById(Id);
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var room = roomResult.get();
        List<RoomUsersProjection> users = new ArrayList<>();

        room.getRoomParticipationSet().stream().forEach(x -> {

            var userProjection = new RoomUsersProjection(x.getUser().getUserName(),
                    x.getUser().getFirstName(), x.getUser().getLastName());

            if (!users.contains(userProjection) && !x.isRequired())
                users.add(userProjection);
        });

        return users;
    }

    @Override
    public Iterable<RoomUsersProjection> getAllRequiredUsers(long Id) {

        var roomResult = conferenceRoomRepository.findById(Id);
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var room = roomResult.get();
        List<RoomUsersProjection> users = new ArrayList<>();

        room.getRoomParticipationSet().stream().forEach(x -> {

            var userProjection = new RoomUsersProjection(x.getUser().getUserName(),
                    x.getUser().getFirstName(), x.getUser().getLastName());

            if (!users.contains(userProjection) && x.isRequired())
                users.add(userProjection);
        });

        return users;

    }

    @Override
    public RoomUsersProjection getOwner(long Id) {

        var roomResult = conferenceRoomRepository.findById(Id);
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var room = roomResult.get();

        var userProjection = new RoomUsersProjection(room.getOwner().getUserName(),
                room.getOwner().getFirstName(), room.getOwner().getLastName());

        return userProjection;
    }

    @Override
    public TeamProjection getTeam(long Id) {
        var roomResult = conferenceRoomRepository.findById(Id);
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var room = roomResult.get();

        var teamProjection = new TeamProjection(room.getTeam().getId(),
                room.getTeam().getName(),room.getTeam().getConsumedConferenceTime(),
                room.getTeam().isHasExhaustedConferenceTime());

        return teamProjection;

    }

    @Override
    public Boolean addUserToRoom(AddUserToConferenceCommand conferenceCommand) {

        var roomResult = conferenceRoomRepository.findById(conferenceCommand.getRoomId());
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var result = appUserRepository.findById(conferenceCommand.getUserName());

        if(result.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.USER_NOT_FOUND);

        var user = result.get();
        var room = roomResult.get();

        var auth = authorityRepository.findByUserName(user.getUserName());

        RoomParticipation entry = null;


        if(auth.get().getAuthority() == UserTypes.GUEST){

            if(!room.isAllowGuests())
                throw new InvalidOperationException(ErrorMessage.GUEST_NOT_ALLOWED);

              entry = new RoomParticipation(user,room,true,conferenceCommand.isRequired(),user.getUserName(),
                    new Date().getTime());
        }
        else{
            entry = new RoomParticipation(user,room,false,conferenceCommand.isRequired(),user.getUserName(),
                    new Date().getTime());
        }

        var savedEntry = roomParticipationRepository.save(entry);

        if(savedEntry == null)
            throw new ItemNotSavedException("error saving participation while adding user.");


        return true;

    }

    @Override
    public Iterable<RoomParticipationProjection> getRoomParticipationSet(long Id) {
        var roomResult = conferenceRoomRepository.findById(Id);
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var room = roomResult.get();
        List<RoomParticipationProjection> participationProjections = new ArrayList<>();

        room.getRoomParticipationSet().stream().forEach(x -> {

            var userProjection = new RoomUsersProjection(x.getUser().getUserName(),
                    x.getUser().getFirstName(), x.getUser().getLastName());

            ConferenceRoomProjection conferenceRoomProjection = new ConferenceRoomProjection(room.getId(),
                    room.getName(),room.isAllowGuests(),room.getOwner(),room.getTeam());

            var projection = new RoomParticipationProjection(userProjection,conferenceRoomProjection,
                    x.isGuest(),x.isRequired(),x.getTimeJoined(),x.getTimeLeft(),x.getGuestEmail());

            participationProjections.add(projection);
        });

        return participationProjections;
    }

    @Override
    public Boolean joinConferenceAsGuest(JoinConferenceAsGuestCommand command) {

        var roomResult = conferenceRoomRepository.findById(command.getRoomId());
        if(roomResult.isEmpty())
            throw new ItemNotFoundException(ErrorMessage.CONFERENCE_ROOM_NOT_FOUND);

        var result = appUserRepository.findById(command.getUserName());

        var room = roomResult.get();
        AppUser guestUser = null;

        if(result.isEmpty())
        {
            guestUser = new AppUser(command.getFirstName(),
                    command.getLastName(), command.getUserName());

            guestUser = appUserRepository.save(guestUser);

            if(guestUser == null)
                throw new ItemNotSavedException(ErrorMessage.USER_NOT_SAVED);

             var userAuthority = new Authority(guestUser.getUserName(),UserTypes.GUEST);

            var savedAuthority = authorityRepository.save(userAuthority);

            if(savedAuthority == null)
                throw new ItemNotSavedException(ErrorMessage.AUTHORITY_NOT_SAVED);

        }

        if(!room.isAllowGuests())
            throw new InvalidOperationException(ErrorMessage.GUEST_NOT_ALLOWED);

        var entry = new RoomParticipation(guestUser,room,true,false,guestUser.getUserName(),
                    new Date().getTime());

        var savedEntry = roomParticipationRepository.save(entry);

        if(savedEntry == null)
            throw new ItemNotSavedException("error saving participation while adding user.");


        return true;

    }
}
