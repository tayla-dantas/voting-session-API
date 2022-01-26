package com.votingsessionAPI.votingsession.infraestructure.dao;

 import com.votingsessionAPI.votingsession.domain.model.ScheduleEntity;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository  extends CrudRepository<ScheduleEntity, Long> {
}
