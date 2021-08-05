package com.tauria.conferenceAPI.models.commands.conference;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class AddUserToConferenceCommand implements Serializable {

    @Email
    private String userName;

    @Min(1)
    private long roomId;

    private boolean isRequired;

    protected AddUserToConferenceCommand(){}

    public AddUserToConferenceCommand(String userName, long roomId, boolean isRequired){
        this.isRequired = isRequired;
        this.roomId = roomId;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public long getRoomId() {
        return roomId;
    }

    public boolean isRequired() {
        return isRequired;
    }
}
