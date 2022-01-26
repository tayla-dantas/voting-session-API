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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "vote")
    String vote;

}
