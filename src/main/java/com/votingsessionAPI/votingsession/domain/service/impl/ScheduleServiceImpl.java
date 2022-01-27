package com.votingsessionAPI.votingsession.domain.service.impl;

import com.votingsessionAPI.votingsession.domain.BusinessException;
import com.votingsessionAPI.votingsession.domain.dto.ScheduleDTO;
import com.votingsessionAPI.votingsession.domain.dto.VotingSessionDTO;
import com.votingsessionAPI.votingsession.domain.model.ScheduleEntity;
import com.votingsessionAPI.votingsession.domain.model.VotingSessionEntity;
import com.votingsessionAPI.votingsession.domain.service.ScheduleService;
import com.votingsessionAPI.votingsession.infraestructure.dao.ScheduleRepository;
import com.votingsessionAPI.votingsession.infraestructure.dao.VotingRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    VotingRepository votingRepository;

    Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Override
    public ScheduleDTO createSchedule(ScheduleDTO schedule) throws BusinessException {
        try {
            logger.warn("Trying to save schedule: {}", schedule);

            ScheduleEntity createdEntity = scheduleRepository.save(modelMapper.map(schedule, ScheduleEntity.class));

            logger.info("Schedule saved successfully!");

            return modelMapper.map(createdEntity, ScheduleDTO.class);
        } catch (Exception e) {
            throw new BusinessException("Error while creating schedule");
        }
    }

    @Override
    public ScheduleDTO getScheduleById(Long id) throws BusinessException {
        logger.warn("Trying to retrieve Schedule with id: {}", id);
        Optional<ScheduleEntity> schedule = scheduleRepository.findById(id);

        if (schedule.isPresent()) {
            logger.info("Schedule retrieved successfully!");
            return modelMapper.map(schedule.get(), ScheduleDTO.class);
        } else {
            throw new BusinessException("Error while getting Schedule with id " + id);
        }
    }

    @Override
    public ScheduleDTO createVotingSession(VotingSessionDTO votingSession) throws BusinessException {
        try {
            logger.warn("Trying to update schedule id: {} and voting session: {}", votingSession.getScheduleId(), votingSession);
            ScheduleEntity scheduleEntity = getUpdatedScheduleEntity(votingSession);
            votingRepository.save(scheduleEntity.getVotingSession());
            scheduleRepository.save(scheduleEntity);


            logger.info("Schedule updated successfully!");

            return getScheduleById(votingSession.getScheduleId());
        } catch (Exception e) {
            throw new BusinessException("Error while updating schedule");
        }
    }

    private ScheduleEntity getUpdatedScheduleEntity(VotingSessionDTO votingSession) {

        Optional<ScheduleEntity> schedule = scheduleRepository.findById(votingSession.getScheduleId());
        ScheduleEntity scheduleEntity =  schedule.get();
        votingSessionEntityMapper(votingSession, scheduleEntity);
        return scheduleEntity;
    }

    private VotingSessionEntity votingSessionEntityMapper(VotingSessionDTO votingSession, ScheduleEntity scheduleEntity) {
        VotingSessionEntity votingSessionEntity = modelMapper.map(votingSession, VotingSessionEntity.class);
        votingSessionEntity.setExpireTime(votingSession.getExpireTime().toString());
        votingSessionEntity.setResult(votingSession.getResult());
        scheduleEntity.setVotingSession(votingSessionEntity);
        return votingSessionEntity;
    }

}
