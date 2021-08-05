package com.tauria.conferenceAPI.models.projections;


public class RoomParticipationProjection {
    private RoomUsersProjection user;
    private ConferenceRoomProjection conferenceRoom;
    private boolean isGuest;
    private boolean isRequired;
    private String guestEmail;
    private long timeJoined;
    private long timeLeft;

    protected RoomParticipationProjection(){}

    public RoomParticipationProjection(RoomUsersProjection user, ConferenceRoomProjection room,
                                       boolean isGuest, boolean isRequired, long timeJoined,
                                       long timeLeft, String guestEmail){
        this.conferenceRoom = room;
        this.guestEmail = guestEmail;
        this.isGuest = isGuest;
        this.isRequired = isRequired;
        this.timeJoined = timeJoined;
        this.timeLeft = timeLeft;
        this.guestEmail = guestEmail;
        this.user = user;

    }


    public RoomUsersProjection getUser() {
        return user;
    }

    public ConferenceRoomProjection getConferenceRoom() {
        return conferenceRoom;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public long getTimeJoined() {
        return timeJoined;
    }

    public long getTimeLeft() {
        return timeLeft;
    }
}

