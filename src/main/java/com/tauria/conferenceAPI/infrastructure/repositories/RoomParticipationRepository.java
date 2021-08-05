package com.tauria.conferenceAPI.infrastructure.repositories;

import com.tauria.conferenceAPI.models.applicationEntities.RoomParticipation;
import com.tauria.conferenceAPI.models.applicationEntities.RoomParticipationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository

@RepositoryRestResource(exported = false)
public interface RoomParticipationRepository extends JpaRepository<RoomParticipation, RoomParticipationKey> {
}
