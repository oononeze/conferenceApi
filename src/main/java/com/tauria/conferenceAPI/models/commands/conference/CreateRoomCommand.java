package com.tauria.conferenceAPI.models.commands.conference;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class CreateRoomCommand {

    private String ownerUserName;

    @Min(1)
    private long teamId;

    @Size(min = 2, max = 50, message = "name length not valid")
    private String name;

    private boolean allowGuests;

    protected CreateRoomCommand(){}

    public CreateRoomCommand(long teamId,
                             boolean allowGuests, String name){
        this.allowGuests = allowGuests;
        this.name = name;
        this.teamId = teamId;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public boolean isAllowGuests() {
        return allowGuests;
    }

    public void setOwnerUserName(String userName){
        this.ownerUserName = userName;
    }

}
