package com.tauria.conferenceAPI.infrastructure.repositories;

import com.tauria.conferenceAPI.models.applicationEntities.AppUser;
import com.tauria.conferenceAPI.models.applicationEntities.Authority;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("Select new com.tauria.conferenceAPI.models.projections.AuthorityProjection" +
            "(a.userName, a.authority) from Authority a " +
            "where a.userName = ?1")
    Optional<Authority> findByUserName(String userName);

}