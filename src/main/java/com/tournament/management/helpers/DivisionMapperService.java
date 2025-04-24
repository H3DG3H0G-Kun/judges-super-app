package com.tournament.management.helpers;

import com.tournament.management.dtos.DivisionRequest;
import com.tournament.management.dtos.DivisionResponse;
import com.tournament.management.entities.Division;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionMapperService {

    public Division toEntity(DivisionRequest request) {
        Division div = new Division();
        div.setName(request.getName());
        div.setDescription(request.getDescription());
        return div;
    }

    public DivisionResponse toResponse(Division div) {
        DivisionResponse res = new DivisionResponse();
        res.setId(div.getId());
        res.setName(div.getName());
        res.setDescription(div.getDescription());
        return res;
    }

    public List<Division> toEntities(List<DivisionRequest> requests) {
        return requests.stream().map(this::toEntity).collect(Collectors.toList());
    }

    public List<DivisionResponse> toResponses(List<Division> divisions) {
        return divisions.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
