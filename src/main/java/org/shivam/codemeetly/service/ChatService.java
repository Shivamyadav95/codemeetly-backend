package org.shivam.codemeetly.service;

import org.shivam.codemeetly.dto.ChatMessageRequest;

public interface ChatService {

    String getChatHistory();

    void saveMessage(ChatMessageRequest request); // ✅ ADD THIS

}