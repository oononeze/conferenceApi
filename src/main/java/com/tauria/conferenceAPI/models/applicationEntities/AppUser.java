package com.tauria.conferenceAPI.models.applicationEntities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class AppUser {

    @NotEmpty(message ="first name cannot be empty")
    private String firstName;

    @NotEmpty(message ="last name cannot be empty")
    private String lastName;

    @Id
    @Email
    private String userName;

    @ManyToMany(mappedBy = "users")
    private List<Team> teams;

    @OneToMany(mappedBy ="owner",
            cascade =  CascadeType.ALL,
             fetch = FetchType.LAZY)
    private List<ConferenceRoom> createdRooms;

    @OneToMany(mappedBy = "user")
    private Set<RoomParticipation> roomParticipationSet;

    protected AppUser(){}

    public AppUser(String firstName, String lastName, String userName){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<ConferenceRoom> getCreatedRooms() {
        return createdRooms;
    }

    public Set<RoomParticipation> getRoomParticipationSet() {
        return roomParticipationSet;
    }

    @Override
    public boolean equals(Object object){

        if (object == this)
            return true;

        if (!(object instanceof AppUser))
            return false;

        AppUser user = (AppUser) object;
        return userName.equals(user.getUserName());
    }
}
