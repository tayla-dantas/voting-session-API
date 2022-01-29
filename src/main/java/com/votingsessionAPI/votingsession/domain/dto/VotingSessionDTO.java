package com.votingsessionAPI.votingsession.domain.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class VotingSessionDTO {

    public static final int DEFAULT_EXPIRE_TIME = 1;

    @Id
    private Long id;

    @NonNull
    private Long scheduleId;

    @NonNull
    private VoteResultDTO result;

    private LocalTime expireTime = LocalTime.now().plusMinutes(DEFAULT_EXPIRE_TIME);

}
