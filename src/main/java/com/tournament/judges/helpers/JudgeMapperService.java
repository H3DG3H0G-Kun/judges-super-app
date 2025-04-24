package com.tournament.judges.helpers;

import com.tournament.judges.dtos.JudgeResponse;
import com.tournament.judges.entities.Judge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeMapperService {

    public JudgeResponse toResponse(Judge judge) {
        JudgeResponse res = new JudgeResponse();
        res.setId(judge.getId());
        res.setName(judge.getName());
        res.setRole(judge.getRole());
        if (judge.getPanel() != null && judge.getPanel().getTournament() != null) {
            res.setTournamentName(judge.getPanel().getTournament().getName());
        }
        return res;
    }


    public List<JudgeResponse> toResponses(List<Judge> judges) {
        return judges.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
