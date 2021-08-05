package com.tauria.conferenceAPI.models.commands.conference;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class JoinConferenceAsGuestCommand {
    @Email
    private String userName;

    @Size(min = 2, max = 50, message = "first name length not valid")
    private String firstName;

    @Size(min = 2, max = 50, message = "last name length not valid")
    private String lastName;

    @Min(1)
    private long roomId;

    protected JoinConferenceAsGuestCommand(){}

    public JoinConferenceAsGuestCommand(String userName, String firstName,
                                        String lastName, long roomId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.roomId = roomId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getRoomId() {
        return roomId;
    }
}
