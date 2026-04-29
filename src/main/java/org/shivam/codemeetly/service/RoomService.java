package org.shivam.codemeetly.service;

import org.shivam.codemeetly.dto.CreateRoomRequest;
import org.shivam.codemeetly.dto.JoinRoomRequest;
import org.shivam.codemeetly.dto.RoomDto;

public interface RoomService {
    RoomDto createRoom(String creatorEmail, CreateRoomRequest request);

    RoomDto joinRoom(String userEmail, JoinRoomRequest request);
}
