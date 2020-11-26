package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.persistence.DAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EgresoService {

    private DAO<Egreso> repo = new DAO<>(Egreso.class);

    public List<Egreso> findAll() {
        return repo.findAll();
    }

    public Egreso find(int egreso) {
        return repo.find(egreso);
    }

    public List<Egreso> getEgresosNoJustificados() {
        return repo.findAll().stream().filter(eg -> eg.getIngresoAsociado() != null).collect(Collectors.toList());
    }

    public void update(List<Egreso> egresos) {
        egresos.stream().forEach(e -> repo.persist(e));
    }
}