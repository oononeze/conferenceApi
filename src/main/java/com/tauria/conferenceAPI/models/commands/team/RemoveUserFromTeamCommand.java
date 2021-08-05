package com.tauria.conferenceAPI.models.commands.team;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class RemoveUserFromTeamCommand {

    @Email
    private String userName;

    @Min(1)
    private long teamId;

    protected RemoveUserFromTeamCommand(){}

    public RemoveUserFromTeamCommand(String userName, long teamId){
        this.userName = userName;
        this.teamId = teamId;
    }

    public String getUserName() {
        return userName;
    }

    public long getTeamId() {
        return teamId;
    }
}
