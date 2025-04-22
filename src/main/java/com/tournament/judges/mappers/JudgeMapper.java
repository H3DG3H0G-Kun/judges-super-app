package com.tournament.judges.mappers;

import com.tournament.judges.dtos.JudgeResponse;
import com.tournament.judges.entities.Judge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JudgeMapper {
    JudgeResponse toResponse(Judge judge);
}
