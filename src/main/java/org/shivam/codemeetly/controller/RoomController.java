package org.shivam.codemeetly.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final Map<String, String> rooms = new HashMap<>();

    // CREATE ROOM
    @PostMapping("/create")
    public Map<String, String> createRoom(@RequestParam String password) {

        String roomId = UUID.randomUUID().toString().substring(0, 6);

        rooms.put(roomId, password);

        return Map.of(
                "roomId", roomId,
                "password", password
        );
    }

    // JOIN ROOM
    @PostMapping("/join")
    public Map<String, String> joinRoom(
            @RequestParam String roomId,
            @RequestParam String password
    ) {

        if (!rooms.containsKey(roomId)) {
            throw new RuntimeException("Room not found");
        }

        if (!rooms.get(roomId).equals(password)) {
            throw new RuntimeException("Wrong password");
        }

        return Map.of("status", "joined");
    }
}