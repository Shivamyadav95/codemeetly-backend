package org.shivam.codemeetly.websocket;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.ChatMessage;
import org.shivam.codemeetly.dto.ChatMessageRequest;
import org.shivam.codemeetly.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat.send/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessage sendMessage(
            @DestinationVariable String roomId,
            ChatMessage message) {

        ChatMessageRequest req = new ChatMessageRequest();
        req.setRoomId(roomId);
        req.setSender(message.getSender());
        req.setMessage(message.getMessage());
        chatService.saveMessage(req);

        message.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return message;
    }
}