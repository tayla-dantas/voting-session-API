package com.votingsessionAPI.votingsession.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBL_RESULTS")
public class VoteResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "votingSessionId")
    private Long votingSessionId;

    @Column(name = "totalAmount")
    private Long totalAmount;

    @Column(name = "option_yes")
    private Long yes;

    @Column(name = "option_no")
    private Long no;

    @Column(name = "finalResult")
    private String finalResult;
}
