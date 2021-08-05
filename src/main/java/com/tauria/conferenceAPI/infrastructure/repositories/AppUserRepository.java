package com.tauria.conferenceAPI.infrastructure.repositories;

import com.tauria.conferenceAPI.models.applicationEntities.AppUser;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository

@RepositoryRestResource(exported = false)
public interface AppUserRepository extends JpaRepository<AppUser, String>  {
}
