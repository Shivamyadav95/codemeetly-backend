package org.shivam.codemeetly.service;

import org.shivam.codemeetly.dto.ChatMessageRequest;
import org.shivam.codemeetly.entity.ChatMessageEntity;

import java.util.List;

public interface ChatService {

    ChatMessageEntity saveMessage(ChatMessageRequest request);

    List<ChatMessageEntity> getMessages(String roomId);
}