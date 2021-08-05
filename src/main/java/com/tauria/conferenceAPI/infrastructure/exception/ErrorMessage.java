package com.tauria.conferenceAPI.infrastructure.exception;

public class ErrorMessage {

    public final static String TEAM_NOT_FOUND = "team not found";
    public final static String CONFERENCE_ROOM_NOT_FOUND = "conference room not found";
    public final static String USER_NOT_FOUND = "user not found";

    public final static String AUTHORITY_NOT_FOUND = "authority not found";
    public final static String AUTHORITY_NOT_SAVED = "authority not saved";

    public final static String TEAM_ALREADY_EXISTS = "team already exists";
    public final static String CONFERENCE_ROOM_ALREADY_EXISTS = "conference room already exists";
    public final static String USER_ALREADY_EXISTS = "user already exists";


    public final static String TEAM_NOT_DELETED = "team not deleted!";
    public final static String CONFERENCE_ROOM__NOT_DELETED = "conference room not deleted!";
    public final static String USER_NOT_DELETED = "user not deleted";

    public final static String ILLEGAL_DELETE = "delete not permitted, one cause might be available child nodes";
    public final static String UNEXPECTED_ERROR= "unexpected error!";
    public final static String BAD_CREDENTIALS = "Bad credentials";
    public final static String GUEST_NOT_ALLOWED = "guests not allowed in this room";


    public final static String TEAM_NOT_SAVED = "team not saved!";
    public final static String CONFERENCE_ROOM_NOT_SAVED = "conference room not saved!";
    public final static String USER_NOT_SAVED = "user not saved";

    public final static String PARTICIPATION_RECORD_NOT_SAVED = "error saving user participation record";

}
