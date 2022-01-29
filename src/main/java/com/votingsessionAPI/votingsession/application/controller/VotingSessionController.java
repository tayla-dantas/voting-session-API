package com.votingsessionAPI.votingsession.application.controller;

import com.votingsessionAPI.votingsession.domain.BusinessException;
import com.votingsessionAPI.votingsession.domain.dto.AssociateDTO;
import com.votingsessionAPI.votingsession.domain.dto.ScheduleDTO;
import com.votingsessionAPI.votingsession.domain.dto.VoteResultDTO;
import com.votingsessionAPI.votingsession.domain.dto.VotingSessionDTO;
import com.votingsessionAPI.votingsession.domain.service.VotingSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VotingSessionController {
    private static final String UPDATE_SCHEDULE_WITH_ID_AND_INFO = "Attempting to update schedule with id: {} and info: {}";
    private static final String SESSION_WITH_ID_AND_VOTE = "Attempting to update votingSession with id: {} and vote: {}";
    private static final String ERROR_IN_VOTING_SESSION_SERVICE = "Error in VotingSessionService: {}";
    private static final String RETRIEVE_VOTES_F0R_VOTING_SESSION = "Attempting to retrive votes from voting session with id: {} ";

    private Logger logger = LoggerFactory.getLogger(VotingSessionController.class);

    @Autowired
    private VotingSessionService votingSessionService;

    @PutMapping("/v1/votingSession")
    public ResponseEntity<ScheduleDTO> createVotingSession(@RequestBody VotingSessionDTO votingSession) {
        try {
            logger.info(UPDATE_SCHEDULE_WITH_ID_AND_INFO, votingSession.getScheduleId(), votingSession);
            return new ResponseEntity<>(votingSessionService.createVotingSession(votingSession), HttpStatus.OK);
        } catch (BusinessException e) {
            logger.error(ERROR_IN_VOTING_SESSION_SERVICE, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/v1/votingSession/vote/{id}")
    public ResponseEntity<AssociateDTO> createVote(@RequestBody AssociateDTO associateDTO) {
        try {
            logger.info(SESSION_WITH_ID_AND_VOTE, associateDTO.getVoteSessionId(), associateDTO);
            return new ResponseEntity<>(votingSessionService.createVote(associateDTO), HttpStatus.OK);
        } catch (BusinessException e) {
            logger.error(ERROR_IN_VOTING_SESSION_SERVICE, e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/v1/votingSession/{votingSessionId}")
    public ResponseEntity<VoteResultDTO> getVotingSessionResult(@PathVariable("votingSessionId") Long id) {
        try {
            logger.info(RETRIEVE_VOTES_F0R_VOTING_SESSION, id);
            return new ResponseEntity<>(votingSessionService.getVotingSessionResult(id), HttpStatus.OK);
        } catch (BusinessException e) {
            logger.error(ERROR_IN_VOTING_SESSION_SERVICE, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
