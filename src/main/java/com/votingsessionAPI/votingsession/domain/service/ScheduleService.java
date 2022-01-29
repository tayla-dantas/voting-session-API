package com.votingsessionAPI.votingsession.domain.service;

import com.votingsessionAPI.votingsession.domain.BusinessException;
import com.votingsessionAPI.votingsession.domain.dto.ScheduleDTO;

public interface ScheduleService {

    ScheduleDTO createSchedule(ScheduleDTO schedule) throws BusinessException;

    ScheduleDTO getScheduleById(Long id) throws BusinessException;

}
