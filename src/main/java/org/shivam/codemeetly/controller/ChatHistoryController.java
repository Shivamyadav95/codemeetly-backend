package org.shivam.codemeetly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.shivam.codemeetly.service.ChatService;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatHistoryController {

    private final ChatService chatService;

    @GetMapping("/history")
    public String getHistory() {
        return chatService.getChatHistory();
    }
}