package org.shivam.codemeetly.repository;

import org.shivam.codemeetly.entity.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewSessionRepository extends JpaRepository<InterviewSession, Long> {
    Optional<InterviewSession> findByRoomId(String roomId);
}