package com.tournament.sportsmen.dtos;

import lombok.Data;

@Data
public class RegisterSportsmanRequest {
    private String fullName;
    private Integer age;
    private String gender;
    private String club;
    private String country;
    private Long tournamentId;
    private Long divisionId;
}

