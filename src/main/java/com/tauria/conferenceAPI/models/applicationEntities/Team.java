package com.tauria.conferenceAPI.models.applicationEntities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name="teams")
public class Team extends BaseBean {

    @NotEmpty(message ="name cannot be empty")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "team_users",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name ="user_id"))
    private List<AppUser> users;

    @OneToMany(mappedBy = "team",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<ConferenceRoom> conferences;

    private long consumedConferenceTime;

    private boolean hasExhaustedConferenceTime;

    protected Team(){}

    public Team(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public boolean addUser(AppUser user){
        if(!users.contains(user)) {
            users.add(user);
            return true;
        }
        return false;
    }

    public List<ConferenceRoom> getConferences() {
        return conferences;
    }

    public long getConsumedConferenceTime() {
        return consumedConferenceTime;
    }

    /*consumed time is set after a user leaves the room.
      if time > 100hrs hasExhaustedConferenceTime is set to false.
    */
    public void setConsumedConferenceTime(long consumedConferenceTime) {
        this.consumedConferenceTime = consumedConferenceTime;
        //TODO check if consumed time greater than 100hrs.
    }

    public boolean isHasExhaustedConferenceTime() {
        return hasExhaustedConferenceTime;
    }

    public void setHasExhaustedConferenceTime(boolean hasExhaustedConferenceTime) {
        this.hasExhaustedConferenceTime = hasExhaustedConferenceTime;
    }

    @Override
    public boolean equals(Object object){

        if (object == this)
            return true;

        if (!(object instanceof Team))
            return false;

        Team team = (Team) object;
        return name.equals(team.getName());
    }
}
