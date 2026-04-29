package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.*;
import org.shivam.codemeetly.entity.*;
import org.shivam.codemeetly.repository.*;
import org.shivam.codemeetly.service.InterviewService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewSessionRepository sessionRepo;
    private final InterviewParticipantRepository participantRepo;

    @Override
    public InterviewSessionDto createInterview(String interviewerEmail, CreateInterviewRequest req) {

        InterviewSession session = InterviewSession.builder()
                .roomId(req.getRoomId())
                .interviewerEmail(interviewerEmail)
                .startTime(new Date())
                .active(true)
                .build();

        sessionRepo.save(session);

        // interviewer entry
        InterviewParticipant interviewer = InterviewParticipant.builder()
                .userEmail(interviewerEmail)
                .role(InterviewRole.INTERVIEWER)
                .session(session)
                .build();

        participantRepo.save(interviewer);

        // candidate entry
        InterviewParticipant candidate = InterviewParticipant.builder()
                .userEmail(req.getCandidateEmail())
                .role(InterviewRole.CANDIDATE)
                .session(session)
                .build();

        participantRepo.save(candidate);

        return mapToDto(session, req.getCandidateEmail());
    }

    @Override
    public InterviewSessionDto joinInterview(String userEmail, Long sessionId) {

        InterviewSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Interview session not found"));

        InterviewParticipant observer = InterviewParticipant.builder()
                .userEmail(userEmail)
                .role(InterviewRole.OBSERVER)
                .session(session)
                .build();

        participantRepo.save(observer);

        // candidateEmail null problem fix
        return mapToDto(session, userEmail);
    }

    @Override
    public InterviewSessionDto endInterview(Long sessionId) {

        InterviewSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Interview session not found"));

        session.setActive(false);
        session.setEndTime(new Date());
        sessionRepo.save(session);

        // candidate email DB से fetch करना पड़ेगा
        String candidateEmail = participantRepo
                .findBySessionIdAndRole(sessionId, InterviewRole.CANDIDATE)
                .map(InterviewParticipant::getUserEmail)
                .orElse(null);

        return mapToDto(session, candidateEmail);
    }

    private InterviewSessionDto mapToDto(InterviewSession session, String candidateEmail) {

        InterviewSessionDto dto = new InterviewSessionDto();

        dto.setSessionId(session.getId());
        dto.setRoomId(session.getRoomId());
        dto.setInterviewerEmail(session.getInterviewerEmail());
        dto.setCandidateEmail(candidateEmail);
        dto.setActive(session.isActive());

        return dto;
    }
}