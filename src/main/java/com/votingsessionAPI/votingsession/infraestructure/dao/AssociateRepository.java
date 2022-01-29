package com.votingsessionAPI.votingsession.infraestructure.dao;

import com.votingsessionAPI.votingsession.domain.model.AssociateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateRepository  extends CrudRepository<AssociateEntity, Long> {

    List<AssociateEntity> findByVoteSessionId(Long id);
}
