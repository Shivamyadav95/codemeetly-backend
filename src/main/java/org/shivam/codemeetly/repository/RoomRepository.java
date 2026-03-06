package org.shivam.codemeetly.repository;

import org.shivam.codemeetly.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}
