package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.persistence.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngresoService {

    @Autowired
    public DAO<Ingreso> repo = new DAO<>(Ingreso.class);

    public List<Ingreso> getIngresos() {
        return repo.findAll();
    }

    public List<Ingreso> getIngresosDisponibles() {
        return repo.findAll().stream().filter(i -> i.valorDisponible() > 0).collect(Collectors.toList());
    }

    public void update(List<Ingreso> ingresos) {
        ingresos.stream().forEach(i -> repo.persist(i));
    }
}
