package com.tauria.conferenceAPI.models.applicationEntities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class RoomParticipationKey implements Serializable {

    @Column(name = "user_id")
    private
    String userName;

    @Column(name = "conference_id")
    private
    long conferenceRoomId;

    public RoomParticipationKey(){}

    public String getUserName() {
        return userName;
    }

    public long getConferenceRoomId() {
        return conferenceRoomId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setConferenceRoomId(long conferenceRoomId) {
        this.conferenceRoomId = conferenceRoomId;
    }

    @Override
    public boolean equals(Object object){

        if (object == this)
            return true;

        if (!(object instanceof RoomParticipationKey))
            return false;

        RoomParticipationKey key = (RoomParticipationKey) object;
        return userName.equals(key.userName)
                && conferenceRoomId == key.conferenceRoomId;
    }

}
