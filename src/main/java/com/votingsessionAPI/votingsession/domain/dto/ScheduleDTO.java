package com.votingsessionAPI.votingsession.domain.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDTO {

    @Id
    private Long id;

    @NonNull
    private String name;

    private VotingSessionDTO votingSession;


}
