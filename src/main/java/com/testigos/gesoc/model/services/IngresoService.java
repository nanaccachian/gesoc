package com.testigos.gesoc.model.services;

import java.util.List;
import java.util.stream.Collectors;

import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.persistence.DAO;

import com.testigos.gesoc.persistence.DAOIngreso;
import org.springframework.stereotype.Service;

@Service
public class IngresoService {

    private DAOIngreso dao = new DAOIngreso();

    public List<Ingreso> getIngresos() {
        return dao.findAll();
    }

    public List<Ingreso> getIngresosDisponibles() {
        return dao.findAllConEgresos().stream().filter(i -> i.valorDisponible() > 0).collect(Collectors.toList());
    }

    public void persist(List<Ingreso> ingresos) {
        ingresos.stream().forEach(i -> dao.persist(i));
    }

    public void persist(Ingreso ingreso) {
        dao.persist(ingreso);
    }

}
