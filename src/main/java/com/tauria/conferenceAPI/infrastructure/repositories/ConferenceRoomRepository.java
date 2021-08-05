package com.tauria.conferenceAPI.infrastructure.repositories;

import com.tauria.conferenceAPI.models.applicationEntities.ConferenceRoom;
import com.tauria.conferenceAPI.models.projections.TeamProjection;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository

@RepositoryRestResource(exported = false)
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {

    @Query("Select new com.tauria.conferenceAPI.models.projections.ConferenceRoomProjection" +
            "(c.id,c.name,c.allowGuests,c.owner,c.team) " +
            "from ConferenceRoom c " +
            "where c.name = ?1")
    Optional<Iterable<TeamProjection>> findByName(String conferenceName);
}
