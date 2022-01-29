package com.votingsessionAPI.votingsession.application.controller;

import com.votingsessionAPI.votingsession.domain.BusinessException;
import com.votingsessionAPI.votingsession.domain.dto.ScheduleDTO;
import com.votingsessionAPI.votingsession.domain.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private static final String CREATE_ASSOCIATE_WITH_INFO = "Attemping to create schedule with info: {}";
    private static final String ERROR_IN_SCHEDULE_SERVICE = "Error in ScheduleService: {}";
    private static final String RETRIEVE_SCHEDULE_WITH_ID = "Attemping to retrieve schedule with id: {}";

    private Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/v1/schedules")
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO schedule) {
        try {
            logger.info(CREATE_ASSOCIATE_WITH_INFO, schedule);
            return new ResponseEntity<>(scheduleService.createSchedule(schedule), HttpStatus.CREATED);
        } catch (BusinessException e) {
            logger.error(ERROR_IN_SCHEDULE_SERVICE, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/schedules/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable("id") Long id) {
        try {
            logger.info(RETRIEVE_SCHEDULE_WITH_ID, id);
            return new ResponseEntity<>(scheduleService.getScheduleById(id), HttpStatus.OK);
        } catch (BusinessException e) {
            logger.error(ERROR_IN_SCHEDULE_SERVICE, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
