package com.votingsessionAPI.votingsession.domain.service;

import com.votingsessionAPI.votingsession.domain.BusinessException;
import com.votingsessionAPI.votingsession.domain.dto.AssociateDTO;
import com.votingsessionAPI.votingsession.domain.dto.ScheduleDTO;
import com.votingsessionAPI.votingsession.domain.dto.VoteResultDTO;
import com.votingsessionAPI.votingsession.domain.dto.VotingSessionDTO;

public interface VotingSessionService {

    ScheduleDTO createVotingSession(VotingSessionDTO votingSession) throws BusinessException;

    AssociateDTO createVote(AssociateDTO associateDTO) throws BusinessException;

    VoteResultDTO getVotingSessionResult(Long id) throws BusinessException;

}
