package com.tournament.sportsmen.dtos;

import com.tournament.common.enums.SportsmanRegistrationStatus;
import lombok.Data;

@Data
public class StatusChangeRequest {
    private SportsmanRegistrationStatus status;
    private String rejectionComment;
}
