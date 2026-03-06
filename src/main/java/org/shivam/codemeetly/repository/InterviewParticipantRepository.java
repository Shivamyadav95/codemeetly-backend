package org.shivam.codemeetly.repository;

import org.shivam.codemeetly.entity.InterviewParticipant;
import org.shivam.codemeetly.entity.InterviewRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewParticipantRepository extends JpaRepository<InterviewParticipant, Long> {

    Optional<InterviewParticipant> findBySessionIdAndRole(Long sessionId, InterviewRole role);

}