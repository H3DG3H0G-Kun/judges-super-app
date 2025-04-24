package com.tournament.sportsmen.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterSportsmanRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String fullName;

    @NotNull
    @Min(5)
    @Max(100)
    private Integer age;

    @NotBlank
    private String gender;

    private String club;

    private String country;

    @NotNull
    private Long tournamentId;

    @NotNull
    private Long divisionId;

}
