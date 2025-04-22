package com.tournament.scoring.mappers;

import com.tournament.scoring.dtos.ScoreResponse;
import com.tournament.scoring.entities.Score;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScoreMapper {
    ScoreResponse toResponse(Score score);
}
