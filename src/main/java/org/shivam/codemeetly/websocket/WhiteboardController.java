package org.shivam.codemeetly.websocket;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.WhiteboardDrawEvent;
import org.shivam.codemeetly.dto.WhiteboardClearEvent;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WhiteboardController {


    @MessageMapping("/whiteboard/draw/{roomId}")
    @SendTo("/topic/whiteboard/draw/{roomId}")
    public WhiteboardDrawEvent draw(
            @DestinationVariable String roomId,
            WhiteboardDrawEvent event) {
        return event;
    }


    @MessageMapping("/whiteboard/clear/{roomId}")
    @SendTo("/topic/whiteboard/clear/{roomId}")
    public WhiteboardClearEvent clear(
            @DestinationVariable String roomId,
            WhiteboardClearEvent event) {
        return event;
    }
}