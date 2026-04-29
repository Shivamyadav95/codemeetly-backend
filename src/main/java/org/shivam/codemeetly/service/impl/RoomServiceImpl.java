package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.CreateRoomRequest;
import org.shivam.codemeetly.dto.JoinRoomRequest;
import org.shivam.codemeetly.dto.RoomDto;
import org.shivam.codemeetly.entity.Room;
import org.shivam.codemeetly.entity.RoomMember;
import org.shivam.codemeetly.repository.RoomRepository;
import org.shivam.codemeetly.repository.RoomMemberRepository;
import org.shivam.codemeetly.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepo;
    private final RoomMemberRepository memberRepo;

    @Override
    public RoomDto createRoom(String creatorEmail, CreateRoomRequest request) {

        String roomId = UUID.randomUUID().toString();

        Room room = Room.builder()
                .roomId(roomId)
                .name(request.getName())
                .createdBy(creatorEmail)
                .createdAt(new Date())
                .build();

        roomRepo.save(room);

        // Creator becomes HOST
        RoomMember host = RoomMember.builder()
                .email(creatorEmail)
                .role("HOST")
                .room(room)
                .build();

        memberRepo.save(host);

        RoomDto dto = new RoomDto();
        dto.setRoomId(roomId);
        dto.setName(room.getName());
        dto.setCreatedBy(creatorEmail);
        dto.setRole("HOST");

        return dto;
    }

    @Override
    public RoomDto joinRoom(String userEmail, JoinRoomRequest request) {

        Room room = roomRepo.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        RoomMember member = RoomMember.builder()
                .email(userEmail)
                .role("PARTICIPANT")
                .room(room)
                .build();

        memberRepo.save(member);

        RoomDto dto = new RoomDto();
        dto.setRoomId(room.getRoomId());
        dto.setName(room.getName());
        dto.setCreatedBy(room.getCreatedBy());
        dto.setRole("PARTICIPANT");

        return dto;
    }
}