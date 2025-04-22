package com.tournament.common.websocket;

import com.tournament.scoring.enums.WebSocketTopic;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public <T> void send(WebSocketTopic topic, Object id, T payload) {
        String destination = topic.format(id);
        messagingTemplate.convertAndSend(destination, payload);
    }
}
