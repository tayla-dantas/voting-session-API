package com.votingsessionAPI.votingsession.infraestructure.dao;

import com.votingsessionAPI.votingsession.domain.model.VotingSessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends CrudRepository<VotingSessionEntity, Long> {
}
