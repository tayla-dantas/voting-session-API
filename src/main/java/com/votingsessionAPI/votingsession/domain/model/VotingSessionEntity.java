package com.votingsessionAPI.votingsession.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_VOTING_SESSIONS")
public class VotingSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "result")
    Integer result;

    @NonNull
    @Column(name = "expireTime")
    String expireTime;

    @NonNull
    @Column(name = "scheduleId")
    Long scheduleId;
}
