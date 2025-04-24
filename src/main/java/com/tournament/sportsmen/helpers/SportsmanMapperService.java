package com.tournament.sportsmen.helpers;

import com.tournament.sportsmen.dtos.RegisterSportsmanRequest;
import com.tournament.sportsmen.dtos.SportsmanResponse;
import com.tournament.sportsmen.entities.Sportsman;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SportsmanMapperService {

    public Sportsman toEntity(RegisterSportsmanRequest request) {
        Sportsman s = new Sportsman();
        s.setFullName(request.getFullName());
        s.setCountry(request.getCountry());
        s.setClub(request.getClub());
        s.setAge(request.getAge());
        return s;
    }

    public SportsmanResponse toResponse(Sportsman s) {
        SportsmanResponse res = new SportsmanResponse();
        res.setId(s.getId());
        res.setFullName(s.getFullName());
        res.setCountry(s.getCountry());
        res.setClub(s.getClub());
        res.setAge(s.getAge());
        res.setStatus(s.getStatus());
        return res;
    }

    public List<SportsmanResponse> toResponses(List<Sportsman> sportsmen) {
        return sportsmen.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
