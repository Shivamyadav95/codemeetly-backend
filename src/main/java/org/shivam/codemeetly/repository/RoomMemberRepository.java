package org.shivam.codemeetly.repository;

import org.shivam.codemeetly.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomMemberRepository extends JpaRepository<RoomMember,Long> {
}
