package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.persistence.DAO;
import com.testigos.gesoc.persistence.DAOEgreso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EgresoService {

    private DAOEgreso repo = new DAOEgreso();

    public List<Egreso> findAll() {
        return repo.findAll();
    }

    public Egreso find(int egreso) {
        return repo.find(egreso);
    }

    public List<Egreso> getEgresosNoJustificados() {
        return repo.findAll().stream().filter(eg -> eg.getIngresoAsociado() != null).collect(Collectors.toList());
    }

    public void persist(List<Egreso> egresos) {
        egresos.stream().forEach(e -> repo.persist(e));
    }

    public void persist(Egreso egreso) {
        repo.persist(egreso);
    }

    public void update(List<Egreso> egresos) {
        for(Egreso e:egresos)
            repo.update(e);
    }
}
