package com.votingsessionAPI.votingsession.domain.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class VotingSessionDTO {

    public static final int DEFAULT_EXPIRE_TIME = 1;

    @Id
    private Long id;

    @NonNull
    Long scheduleId ;

    Integer result = 0;

    LocalTime expireTime = LocalTime.now().plusMinutes(DEFAULT_EXPIRE_TIME);

    public void setExpireTime(LocalTime expireTime) {
        this.expireTime = expireTime;
    }
}
