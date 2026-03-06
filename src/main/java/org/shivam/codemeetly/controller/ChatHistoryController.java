package org.shivam.codemeetly.controller;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.entity.ChatMessageEntity;
import org.shivam.codemeetly.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatService chatService;

    @GetMapping("/{roomId}")
    public List<ChatMessageEntity> getMessages(@PathVariable String roomId) {
        return chatService.getMessages(roomId);
    }
}