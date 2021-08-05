package com.tauria.conferenceAPI.models.applicationEntities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name ="conference_rooms")
public class ConferenceRoom extends BaseBean {

    @NotEmpty(message ="conference name cannot be empty")
    private String name;

    private boolean allowGuests;

    @ManyToOne
    @JoinColumn(name ="created_by_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser owner;

    @ManyToOne
    @JoinColumn(name ="team_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Team team;

    @OneToMany(mappedBy = "conferenceRoom")
    private Set<RoomParticipation> roomParticipationSet;

    protected ConferenceRoom(){}

    public ConferenceRoom(String name,
                          AppUser owner,
                          Team team, boolean allowGuests){
        setOwner(owner);
        setAllowGuests(allowGuests);
        setName(name);
        setTeam(team);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowGuests() {
        return allowGuests;
    }

    public void setAllowGuests(boolean allowGuests) {
        this.allowGuests = allowGuests;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Set<RoomParticipation> getRoomParticipationSet() {
        return roomParticipationSet;
    }

    public void setRoomParticipationSet(Set<RoomParticipation> roomParticipationSet) {
        this.roomParticipationSet = roomParticipationSet;
    }

    @Override
    public boolean equals(Object object){

        if (object == this)
            return true;

        if (!(object instanceof ConferenceRoom))
            return false;

        ConferenceRoom room = (ConferenceRoom) object;
        return name.equals(room.getName());
    }
}
