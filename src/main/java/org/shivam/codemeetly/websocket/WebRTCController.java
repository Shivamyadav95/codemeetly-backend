package org.shivam.codemeetly.websocket;

import lombok.RequiredArgsConstructor;
import org.shivam.codemeetly.dto.*;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebRTCController {


    @MessageMapping("/webrtc/offer/{roomId}")
    @SendTo("/topic/webrtc/offer/{roomId}")
    public WebRTCOffer sendOffer(
            @DestinationVariable String roomId,
            WebRTCOffer offer) {
        return offer;
    }


    @MessageMapping("/webrtc/answer/{roomId}")
    @SendTo("/topic/webrtc/answer/{roomId}")
    public WebRTCAnswer sendAnswer(
            @DestinationVariable String roomId,
            WebRTCAnswer answer) {
        return answer;
    }


    @MessageMapping("/webrtc/candidate/{roomId}")
    @SendTo("/topic/webrtc/candidate/{roomId}")
    public WebRTCIceCandidate sendIceCandidate(
            @DestinationVariable String roomId,
            WebRTCIceCandidate ice) {
        return ice;
    }
}