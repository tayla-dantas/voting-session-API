package com.votingsessionAPI.votingsession.domain.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_SCHEDULES")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @OneToOne(cascade=CascadeType.MERGE)
    private VotingSessionEntity votingSession;

}
