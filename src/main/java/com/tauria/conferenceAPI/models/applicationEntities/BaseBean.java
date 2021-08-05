package com.tauria.conferenceAPI.models.applicationEntities;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseBean {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private long id;

    @CreationTimestamp
    @Column(name = "date_created",
            nullable = false)
    private Date dateCreated;

    @Column(name = "date_modified",
            nullable = false)
    private Date dateModified;

    protected BaseBean(){
    }

    public long getId(){
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @PrePersist
    void onCreate() {
        this.setDateCreated(new Date());
        this.setDateModified(new Date());
    }

    @PreUpdate
    void onUpdate() {
        this.setDateModified(new Date());
    }

}
