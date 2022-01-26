package com.votingsessionAPI.votingsession.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
public class ScheduleDTO {

    @NonNull
    String name;

    @NonNull
    VotingSessionDTO votingSession;

}
