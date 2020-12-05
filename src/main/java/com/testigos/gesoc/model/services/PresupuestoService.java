package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.Presupuesto;
import com.testigos.gesoc.persistence.DAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresupuestoService {

    DAO<Presupuesto> repo = new DAO<>(Presupuesto.class);

    public List<Presupuesto> findAll() {
        return repo.findAll();
    }
}
