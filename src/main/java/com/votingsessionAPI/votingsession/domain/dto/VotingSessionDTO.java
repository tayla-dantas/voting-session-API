package com.votingsessionAPI.votingsession.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class VotingSessionDTO {

    private static final int DEFAULT_EXPIRE_TIME = 1;
    @NonNull
    Integer result;

    @NonNull
    LocalTime expireTime = LocalTime.now().plusMinutes(DEFAULT_EXPIRE_TIME);

    public void setExpireTime(LocalTime expireTime) {
        this.expireTime = expireTime;
    }
}
