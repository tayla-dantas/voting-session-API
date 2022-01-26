package com.votingsessionAPI.votingsession.infraestructure.dao;

import com.votingsessionAPI.votingsession.domain.model.AssociateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository  extends CrudRepository<AssociateEntity, Long> {
}
