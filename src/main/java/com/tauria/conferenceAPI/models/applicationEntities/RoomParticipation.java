package com.tauria.conferenceAPI.models.applicationEntities;

import javax.persistence.*;

@Entity
public class RoomParticipation {

    @EmbeddedId
    private RoomParticipationKey id;

    @ManyToOne
    @MapsId("userName")
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @MapsId("conferenceRoomId")
    @JoinColumn(name = "conference_id")
    private ConferenceRoom conferenceRoom;

    @Column(name = "is_guest")
    private boolean isGuest;

    @Column(name ="is_required")
    private boolean isRequired;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "time_joined")
    private long timeJoined;

    @Column(name = "time_left")
    private long timeLeft;

    protected RoomParticipation(){}

    public RoomParticipation(AppUser user, ConferenceRoom room,boolean isGuest,
                             boolean isRequired, String guestEmail, long timeJoined){
        this.user = user;
        this.conferenceRoom = room;
        this.isGuest = isGuest;
        this.isRequired = isRequired;
        this.guestEmail = guestEmail;
        this.timeJoined = timeJoined;
        this.id = new RoomParticipationKey();
    }

    public RoomParticipationKey getId() {
        return id;
    }

    public AppUser getUser() {
        return user;
    }

    public ConferenceRoom getConferenceRoom() {
        return conferenceRoom;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void setGuest(boolean guest) {
        isGuest = guest;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
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

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }

    @Override
    public boolean equals(Object object){

        if (object == this)
            return true;

        if (!(object instanceof RoomParticipation))
            return false;

        RoomParticipation roomParticipation = (RoomParticipation) object;
        return user.getUserName()
                .equals(roomParticipation.getUser().getUserName())
                && conferenceRoom.getName()
                .equals(roomParticipation.getConferenceRoom().getName())
                && getConferenceRoom().getTeam()
                .equals(roomParticipation.getConferenceRoom().getTeam().getName());
    }
}
