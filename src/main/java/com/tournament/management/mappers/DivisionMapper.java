package com.tournament.management.mappers;

import com.tournament.management.dtos.DivisionRequest;
import com.tournament.management.dtos.DivisionResponse;
import com.tournament.management.entities.Division;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DivisionMapper {
    Division toEntity(DivisionRequest request);
    DivisionResponse toResponse(Division division);
    List<Division> toEntities(List<DivisionRequest> requests);
    List<DivisionResponse> toResponses(List<Division> divisions);
}