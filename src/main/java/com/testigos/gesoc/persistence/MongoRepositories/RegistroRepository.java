package com.testigos.gesoc.persistence.MongoRepositories;

import java.util.List;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;

import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends MongoRepository<Registro, MongoId> {

    Registro findByDescripcion(String bob);

    List<Registro> findAllByTipoRegistro(TipoRegistro bob);

    List<Registro> findAllByEntidad(Object entidad);
}
