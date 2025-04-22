package com.tournament.sportsmen.dtos;

import com.tournament.common.enums.SportsmanRegistrationStatus;
import lombok.Data;

@Data
public class SportsmanResponse {
    private Long id;
    private String fullName;
    private Integer age;
    private String gender;
    private String club;
    private String country;
    private String tournamentName;
    private String divisionName;
    private SportsmanRegistrationStatus status;
    private String rejectionComment;
}
