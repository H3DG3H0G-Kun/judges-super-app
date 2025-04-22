package com.tournament.scoring.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SportsmanMessage {
    private Long sportsmanId;
    private String fullName;
    private int startNumber;
    private String country;
    private String club;
}
