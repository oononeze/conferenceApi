package com.tauria.conferenceAPI.models.projections;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AuthorityProjection implements Serializable {
    @NotNull
    @Email
    private String userName;

    @NotNull
    private String authority;

    protected AuthorityProjection(){}

    public AuthorityProjection(String userName, String authority){
        this.userName = userName;
        this.authority = authority;
    }

    public String getUserName() {
        return userName;
    }

    public String getAuthority() {
        return authority;
    }
}
