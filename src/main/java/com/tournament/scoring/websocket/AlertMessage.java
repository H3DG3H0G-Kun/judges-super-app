package com.tournament.scoring.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertMessage {
    private Long divisionId;
    private String message;
    private String severity; // INFO, WARNING, CRITICAL
}
