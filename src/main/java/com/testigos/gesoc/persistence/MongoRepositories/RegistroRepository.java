package com.testigos.gesoc.persistence.MongoRepositories;

import com.testigos.gesoc.model.ABM.Registro;
import com.testigos.gesoc.model.ABM.TipoRegistro;
import com.testigos.gesoc.model.domain.entidades.Entidad;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRepository extends MongoRepository<Registro, MongoId> {

    Registro findByDescripcion(String bob);
    List<Registro> findAllByTipoRegistro(TipoRegistro bob);

//    List<Registro> findAllByTipoRegistroAlta();
//    List<Registro> findAllByTipoRegistroBaja();
//    List<Registro> findAllByTipoRegistroModificacion();
    List<Registro> findAllByEntidad(Entidad entidad);
}
