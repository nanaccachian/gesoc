package com.testigos.gesoc.model.services;

import java.util.List;
import java.util.stream.Collectors;

import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import com.testigos.gesoc.model.domain.entidades.Entidad;
import com.testigos.gesoc.persistence.DAOEgresoCP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EgresoCPService {

    @Autowired
    private DAOEgresoCP repo;

    public void persist(EgresoConPresupuestos eg) {
        repo.persist(eg);
    }

    public void updatePresupuesto(EgresoConPresupuestos egresoActual) {
        repo.updatePresupuesto(egresoActual);
    }

    public EgresoConPresupuestos findConPresupuestos(int id) {
        return repo.findConPresupuestos(id);
    }

    public List<EgresoConPresupuestos> findAllConProveedor(Entidad entidad) {
        return repo.findAllConProveedor().stream().filter(e -> e.getComprador().getId() == entidad.getId())
                .collect(Collectors.toList());
    }
}
