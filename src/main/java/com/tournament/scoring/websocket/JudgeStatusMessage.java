package com.tournament.scoring.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeStatusMessage {
    private Long judgeId;
    private String judgeName;
    private boolean ready;
}
