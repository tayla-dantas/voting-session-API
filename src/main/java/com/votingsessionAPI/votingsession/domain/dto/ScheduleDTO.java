package com.votingsessionAPI.votingsession.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class ScheduleDTO {

    @Id
    Long id;

    @NonNull
    String name;

    VotingSessionDTO votingSession;


}
