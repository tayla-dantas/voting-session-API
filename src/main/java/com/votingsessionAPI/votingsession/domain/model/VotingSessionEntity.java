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

    @OneToOne(cascade=CascadeType.MERGE)
    private VoteResultEntity result;

    @NonNull
    @Column(name = "expireTime")
    private String expireTime;

    @NonNull
    @Column(name = "scheduleId")
    private Long scheduleId;
}
