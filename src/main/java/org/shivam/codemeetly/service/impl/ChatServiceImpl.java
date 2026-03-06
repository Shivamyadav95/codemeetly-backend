package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.ChatMessageRequest;
import org.shivam.codemeetly.entity.ChatMessageEntity;
import org.shivam.codemeetly.repository.ChatRepository;
import org.shivam.codemeetly.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public ChatMessageEntity saveMessage(ChatMessageRequest req) {

        ChatMessageEntity msg = ChatMessageEntity.builder()
                .roomId(req.getRoomId())
                .sender(req.getSender())
                .message(req.getMessage())
                .timestamp(System.currentTimeMillis())
                .build();

        return chatRepository.save(msg);
    }

    @Override
    public List<ChatMessageEntity> getMessages(String roomId) {
        return chatRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }
}