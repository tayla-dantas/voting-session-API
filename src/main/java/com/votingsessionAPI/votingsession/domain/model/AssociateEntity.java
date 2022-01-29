package com.votingsessionAPI.votingsession.domain.model;

import com.votingsessionAPI.votingsession.domain.enums.Vote;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TBL_ASSOCIATES")
public class AssociateEntity {

    @Id
    private Long id;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "vote")
    private Vote vote;

    @NonNull
    @Column(name = "voteSessionId")
    private Long voteSessionId;

}
