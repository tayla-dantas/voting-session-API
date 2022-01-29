package com.votingsessionAPI.votingsession.infraestructure.dao;

import com.votingsessionAPI.votingsession.domain.model.VoteResultEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingResultRepository extends CrudRepository<VoteResultEntity, Long> {
}
