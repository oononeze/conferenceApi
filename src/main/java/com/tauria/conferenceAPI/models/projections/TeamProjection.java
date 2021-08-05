package com.tauria.conferenceAPI.models.projections;

import java.io.Serializable;
import java.util.List;

public class TeamProjection  implements Serializable {

    private long id;
    private String name;
    private long consumedConferenceTime;
    private boolean hasExhaustedConferenceTime;

    protected TeamProjection(){}

    public TeamProjection(long id,String name,long consumedConferenceTime,
                             boolean hasExhaustedConferenceTime){
        this.consumedConferenceTime = consumedConferenceTime;
        this.hasExhaustedConferenceTime = hasExhaustedConferenceTime;
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getConsumedConferenceTime() {
        return consumedConferenceTime;
    }

    public boolean isHasExhaustedConferenceTime() {
        return hasExhaustedConferenceTime;
    }
}
