package org.shivam.codemeetly.websocket;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.CodeSyncMessage;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CodeSyncController {

    @MessageMapping("/code.sync/{roomId}")
    @SendTo("/topic/code/{roomId}")
    public CodeSyncMessage sync(
            @DestinationVariable String roomId,
            CodeSyncMessage message) {

        return message;
    }
}