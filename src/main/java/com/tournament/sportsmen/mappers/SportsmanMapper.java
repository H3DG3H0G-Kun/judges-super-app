package com.tournament.sportsmen.mappers;

import com.tournament.sportsmen.dtos.SportsmanResponse;
import com.tournament.sportsmen.entities.Sportsman;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SportsmanMapper {
    SportsmanResponse toResponse(Sportsman sportsman);
}
