package com.votingsessionAPI.votingsession.domain.dto;

import com.votingsessionAPI.votingsession.domain.enums.Vote;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class AssociateDTO {

    @Id
    private Long id;

    @NonNull
    String vote;

}
