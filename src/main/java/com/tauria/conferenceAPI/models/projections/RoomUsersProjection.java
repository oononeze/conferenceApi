package com.tauria.conferenceAPI.models.projections;

import java.io.Serializable;

/**
 * Projection for required,optional and all users for a conference room.
 */
public class RoomUsersProjection implements Serializable {

    private String userName;
    private String firstName;
    private String lastName;

    protected RoomUsersProjection(){}

    public RoomUsersProjection(String userName, String firstName,
                                String lastName){
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
