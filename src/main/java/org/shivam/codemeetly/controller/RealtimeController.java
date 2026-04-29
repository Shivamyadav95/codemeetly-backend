package org.shivam.codemeetly.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class RealtimeController {

    private final SimpMessagingTemplate template;

    public RealtimeController(SimpMessagingTemplate template) {
        this.template = template;
    }

    // ================= CHAT =================
    @MessageMapping("/room.chat/{roomId}")
    public void chat(@DestinationVariable String roomId,
                     Map<String, Object> msg) {

        template.convertAndSend(
                "/topic/chat/" + roomId,
                (Object) msg
        );
    }

    // ================= SIGNAL =================
    @MessageMapping("/webrtc.signal/{roomId}")
    public void signal(@DestinationVariable String roomId,
                       Map<String, Object> data) {

        template.convertAndSend(
                "/topic/signal/" + roomId,
                (Object) data
        );
    }
}