package org.shivam.codemeetly.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.shivam.codemeetly.dto.ChatMessageRequest;
import org.shivam.codemeetly.service.ChatService;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    @Override
    public String getChatHistory() {
        return "Chat history working ✅";
    }

    @Override
    public void saveMessage(ChatMessageRequest request) {
        // ✅ For now just print (later save to DB)
        System.out.println("Message from: " + request.getSender());
        System.out.println("Message: " + request.getContent());
    }
}