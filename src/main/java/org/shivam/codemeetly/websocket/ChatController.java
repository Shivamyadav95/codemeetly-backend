package org.shivam.codemeetly.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.shivam.codemeetly.dto.ChatMessageRequest;
import org.shivam.codemeetly.service.ChatService;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/sendMessage")
    public void sendMessage(String message) {

        ChatMessageRequest req = new ChatMessageRequest();

        req.setSender("user");        // example
        req.setContent(message);      // ✅ FIXED LINE
        req.setRoomId("room1");       // example

        chatService.saveMessage(req);
    }
}