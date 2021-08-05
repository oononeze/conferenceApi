package com.tauria.conferenceAPI.models.commands.team;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class CreateTeamCommand {

    @Size(min = 2, max = 50, message = "name length not valid")
    private String name;

    private String userName;

    protected CreateTeamCommand(){}

    public CreateTeamCommand(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUserName(){return userName;}

    public void setUserName(String username){
        this.userName = username;
    }
}
