package com.tauria.conferenceAPI.infrastructure.repositories;

import com.tauria.conferenceAPI.models.applicationEntities.Team;
import com.tauria.conferenceAPI.models.projections.TeamProjection;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("Select new com.tauria.conferenceAPI.models.projections.TeamProjection" +
            "(t.id,t.name,t.consumedConferenceTime,t.hasExhaustedConferenceTime) " +
            "from Team t " +
            "where t.name = ?1")
    Optional<Iterable<TeamProjection>> findByTeamName(String teamName);
}

