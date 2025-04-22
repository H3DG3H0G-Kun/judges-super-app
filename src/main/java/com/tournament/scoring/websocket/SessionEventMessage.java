package com.tournament.scoring.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionEventMessage {
    private Long divisionId;
    private String eventType; // SESSION_STARTED, SESSION_ENDED
    private String triggeredBy;
}
