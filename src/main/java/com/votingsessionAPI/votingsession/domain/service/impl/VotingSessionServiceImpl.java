package com.votingsessionAPI.votingsession.domain.service.impl;

import com.votingsessionAPI.votingsession.domain.BusinessException;
import com.votingsessionAPI.votingsession.domain.dto.AssociateDTO;
import com.votingsessionAPI.votingsession.domain.dto.ScheduleDTO;
import com.votingsessionAPI.votingsession.domain.dto.VoteResultDTO;
import com.votingsessionAPI.votingsession.domain.dto.VotingSessionDTO;
import com.votingsessionAPI.votingsession.domain.enums.Vote;
import com.votingsessionAPI.votingsession.domain.model.AssociateEntity;
import com.votingsessionAPI.votingsession.domain.model.ScheduleEntity;
import com.votingsessionAPI.votingsession.domain.model.VoteResultEntity;
import com.votingsessionAPI.votingsession.domain.model.VotingSessionEntity;
import com.votingsessionAPI.votingsession.domain.service.ScheduleService;
import com.votingsessionAPI.votingsession.domain.service.VotingSessionService;
import com.votingsessionAPI.votingsession.infraestructure.dao.AssociateRepository;
import com.votingsessionAPI.votingsession.infraestructure.dao.ScheduleRepository;
import com.votingsessionAPI.votingsession.infraestructure.dao.VotingRepository;
import com.votingsessionAPI.votingsession.infraestructure.dao.VotingResultRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    private final ModelMapper modelMapper = new ModelMapper();

    private  Logger logger = LoggerFactory.getLogger(VotingSessionServiceImpl.class);

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private VotingResultRepository votingResultRepository;

    @Override
    public ScheduleDTO createVotingSession(VotingSessionDTO votingSession) throws BusinessException {
        try {

           Optional<ScheduleEntity> scheduleEntity = scheduleRepository.findById(votingSession.getScheduleId());
           Optional<VotingSessionEntity> votingSessionEntity = votingRepository.findById(votingSession.getScheduleId());

            VotingSessionEntity updatedVotingSession = votingSessionEntityMapper(votingSessionEntity.get(), votingSession);
            scheduleEntity.get().setVotingSession(updatedVotingSession);
            votingRepository.save(scheduleEntity.get().getVotingSession());

           return modelMapper.map(scheduleRepository.save(scheduleEntity.get()), ScheduleDTO.class);
          } catch (Exception e) {
            throw new BusinessException("Error while updating schedule");
        }
    }

    @Override
    public AssociateDTO createVote(AssociateDTO associateDTO) throws BusinessException {
        try {
            logger.warn("Trying to update schedule id: {} and associate: {}", associateDTO.getVoteSessionId(), associateDTO);

            Optional<VotingSessionEntity> votingSession = votingRepository.findById(associateDTO.getVoteSessionId());

            LocalTime expireTime = LocalTime.parse(votingSession.get().getExpireTime());

            if ( LocalTime.now().isAfter(expireTime)){
                throw new Exception("Only one vote is allowed per associate");
            }

            if (verifyIfAssociateAlreadyVoted(associateDTO) == false){
                AssociateEntity savedAssociateEntity = associateRepository.save(modelMapper.map(associateDTO, AssociateEntity.class));
                logger.info("associate saved successfully! :{}", associateDTO);
                return modelMapper.map(savedAssociateEntity, AssociateDTO.class);
            }

            throw new Exception("Only one vote is allowed per associate");
        } catch (Exception e) {
            throw new BusinessException("Error while creating vote");
        }
    }

    private boolean verifyIfAssociateAlreadyVoted(AssociateDTO associateDTO) {
        Optional<AssociateEntity> associateEntity = associateRepository.findById(associateDTO.getId());

        if( associateEntity.isPresent()){
            if(associateEntity.get().getId() == associateDTO.getId()){
                return true;
            }
        }

        return  false;
    }

    @Override
    public VoteResultDTO getVotingSessionResult(Long id) throws BusinessException {

        try {
            logger.warn("Trying to process votingresult in voting session with id: {} ", id);
            List<AssociateEntity> associateEntities = associateRepository.findByVoteSessionId(id);

            Long yes = getYes(associateEntities);
            Long no = getNo(associateEntities);

            VoteResultEntity result = getResult(id,calculateResult(yes, no),getAmount(associateEntities), yes, no);
            votingResultRepository.save(result);

            return (modelMapper.map(result, VoteResultDTO.class));
        } catch (Exception e) {
            throw new BusinessException("Error while updating schedule");
        }
    }

    private Long getAmount(List<AssociateEntity> associateEntities) {
        return associateEntities.stream().count();
    }

    private Long getNo(List<AssociateEntity> associateEntities) {
        return associateEntities.stream().filter(e -> e.getVote().getDescription().equals("no")).count();
    }

    private Long getYes(List<AssociateEntity> associateEntities) {
        return associateEntities.stream().filter(e -> e.getVote().getDescription().equals("yes")).count();
    }

    private String calculateResult(Long yesVotes, Long noVotes) {
        return yesVotes > noVotes ? Vote.YES.getDescription(): Vote.NO.getDescription();
    }

    private VotingSessionEntity votingSessionEntityMapper(VotingSessionEntity votingSessionEntity, VotingSessionDTO votingSession) {
        votingSessionEntity.setExpireTime(votingSession.getExpireTime().toString());
        votingSessionEntity.setResult(getResultFromEntity(votingSessionEntity.getScheduleId(),votingSessionEntity.getId(), "NO VOTES",0L, 0L, 0L));
        return votingSessionEntity;
    }

    private VoteResultEntity getResultFromEntity(Long scheduleId, Long sessionId, String finalResult, Long totalAmount, Long yes, Long no){
        VoteResultEntity voteResultEntity = verifyVoteResultEntity(scheduleId);
        voteResultEntity.setVotingSessionId(sessionId);
        voteResultEntity.setFinalResult(finalResult);
        voteResultEntity.setTotalAmount(totalAmount);
        voteResultEntity.setNo(no);
        voteResultEntity.setYes(yes);
        votingResultRepository.save(voteResultEntity);
        return  voteResultEntity;
    }

    private VoteResultEntity verifyVoteResultEntity(Long scheduleId){
        Optional<VoteResultEntity> voteResultEntity = votingResultRepository.findById(scheduleId);
        if (voteResultEntity.isEmpty()){
            return new VoteResultEntity();
        }
        return  voteResultEntity.get();
    }

    private VoteResultEntity getResult(Long id, String finalResult, Long totalAmount, Long yes, Long no) {
        return VoteResultEntity.builder().votingSessionId(id).finalResult(finalResult).totalAmount(totalAmount).yes(yes).no(no).build();
    }


}
