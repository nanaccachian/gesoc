package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository repo;

    public List<Registro> findAll() {
        return repo.findAll();
    }

    //ACA SE PUEDE ENCAPSULAR MEJOR
    public List<Registro> findModificaciones() {
        return repo.findAllByTipoRegistro(TipoRegistro.MODIFICACION);
    }
    public List<Registro> findAltas() {
        return repo.findAllByTipoRegistro(TipoRegistro.ALTA);
    }
    public List<Registro> findBajas() {
        return repo.findAllByTipoRegistro(TipoRegistro.BAJA);
    }

    public void save(Registro reg) {
        repo.save(reg);
    }

}
