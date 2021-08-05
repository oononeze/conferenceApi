package com.tauria.conferenceAPI.models.projections;

import com.tauria.conferenceAPI.models.applicationEntities.AppUser;
import com.tauria.conferenceAPI.models.applicationEntities.Team;

public class ConferenceRoomProjection {

    private long id;
    private String name;
    private boolean allowGuests;
    private RoomUsersProjection owner;
    private TeamProjection team;

    protected  ConferenceRoomProjection(){}

    public ConferenceRoomProjection(long id, String name, boolean allowGuests,
                                    AppUser owner, Team team){
        this.id = id;
        this.allowGuests = allowGuests;
        this.name = name;
        this.owner = new RoomUsersProjection(owner.getUserName(),
                owner.getFirstName(),owner.getLastName());
        this.team = new TeamProjection(team.getId(),team.getName(),
                team.getConsumedConferenceTime(),team.isHasExhaustedConferenceTime());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAllowGuests() {
        return allowGuests;
    }

    public RoomUsersProjection getOwner() {
        return owner;
    }

    public TeamProjection getTeam() {
        return team;
    }
}
