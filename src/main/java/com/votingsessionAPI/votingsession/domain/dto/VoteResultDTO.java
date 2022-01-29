package com.votingsessionAPI.votingsession.domain.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class VoteResultDTO {

    @Id
    private Long id;

    @NonNull
    private Long votingSessionId;

    @NonNull
    private Long totalAmount;

    @NonNull
    private Long yes;

    @NonNull
    private Long no;

    @NonNull
    private String finalResult;
}
