package com.tauria.conferenceAPI.models.commands.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class CreateUserCommand {

    @Email
    private String userName;

    @Size(min = 2, max = 50, message = "first name length not valid")
    private String firstName;

    @Size(min = 2, max = 50, message = "first name length not valid")
    private String lastName;

    protected CreateUserCommand(){}

    public CreateUserCommand(String userName, String lastName,
                             String firstName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
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
}
