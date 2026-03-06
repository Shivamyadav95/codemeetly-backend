package org.shivam.codemeetly.controller;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.CreateRoomRequest;
import org.shivam.codemeetly.dto.JoinRoomRequest;
import org.shivam.codemeetly.dto.RoomDto;
import org.shivam.codemeetly.service.RoomService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/create")
    public RoomDto createRoom(
            @RequestHeader("email") String email,
            @RequestBody CreateRoomRequest request) {

        return roomService.createRoom(email, request);
    }

    @PostMapping("/join")
    public RoomDto joinRoom(
            @RequestHeader("email") String email,
            @RequestBody JoinRoomRequest request) {

        return roomService.joinRoom(email, request);
    }
}
