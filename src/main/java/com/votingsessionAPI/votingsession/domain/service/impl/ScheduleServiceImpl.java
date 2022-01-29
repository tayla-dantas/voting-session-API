package com.votingsessionAPI.votingsession.domain.service.impl;

import com.votingsessionAPI.votingsession.domain.BusinessException;
import com.votingsessionAPI.votingsession.domain.dto.ScheduleDTO;
import com.votingsessionAPI.votingsession.domain.dto.VoteResultDTO;
import com.votingsessionAPI.votingsession.domain.dto.VotingSessionDTO;
import com.votingsessionAPI.votingsession.domain.model.ScheduleEntity;

import com.votingsessionAPI.votingsession.domain.model.VoteResultEntity;
import com.votingsessionAPI.votingsession.domain.model.VotingSessionEntity;
import com.votingsessionAPI.votingsession.domain.service.ScheduleService;
import com.votingsessionAPI.votingsession.infraestructure.dao.AssociateRepository;
import com.votingsessionAPI.votingsession.infraestructure.dao.ScheduleRepository;
import com.votingsessionAPI.votingsession.infraestructure.dao.VotingRepository;
import com.votingsessionAPI.votingsession.infraestructure.dao.VotingResultRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ModelMapper modelMapper = new ModelMapper();

    private Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private VotingResultRepository votingResultRepository;
    @Override
    public ScheduleDTO createSchedule(ScheduleDTO schedule) throws BusinessException {
        try {
            logger.warn("Trying to save schedule: {}", schedule);

            ScheduleEntity createdEntity = modelMapper.map(schedule, ScheduleEntity.class);
            //createdEntity.setVotingSession(getDefaultVotingSession(createdEntity.getId()));
            scheduleRepository.save(createdEntity);
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



}
