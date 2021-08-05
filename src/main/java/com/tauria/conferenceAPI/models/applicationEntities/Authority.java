package com.tauria.conferenceAPI.models.applicationEntities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authorities")
public class Authority extends BaseBean {

    @NotNull
    @Email
    private String userName;

    @NotNull
    private String authority;

    public Authority(String userName, String authority){
        this.userName = userName;
        this.authority = authority;
    }

    protected Authority(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object object){

        if (object == this)
            return true;

        if (!(object instanceof Authority))
            return false;

        Authority auth = (Authority) object;
        return userName.equals(auth.getUserName())
                && authority.equals(auth.authority);
    }

}
