package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.financiamiento.ProyectoDeFinanciamiento;
import com.testigos.gesoc.persistence.DAOProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoDeFinanciamientoService {

    @Autowired
    private DAOProyecto repo;

    public List<ProyectoDeFinanciamiento> findAll() {
        return repo.findAll();
    }

    public void persist(ProyectoDeFinanciamiento proyecto) {
        repo.persist(proyecto);
    }

    public List<ProyectoDeFinanciamiento> findAllConUsuario() {
        return repo.findAllConUsuario();
    }

    public ProyectoDeFinanciamiento find(int proyecto_id) {
        return repo.findConIngresos(proyecto_id);
    }

    public void update(ProyectoDeFinanciamiento proyecto) {
        repo.update(proyecto);
    }
}
