package com.testigos.gesoc.persistence.MongoRepositories;

import com.testigos.gesoc.model.ABM.Alta;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AltaRepository extends MongoRepository<Alta, MongoId> {

    Alta findByDescripcion(String bob);
}
