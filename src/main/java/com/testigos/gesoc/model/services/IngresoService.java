package com.testigos.gesoc.model.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.testigos.gesoc.model.domain.entidades.Entidad;
import com.testigos.gesoc.model.domain.financiamiento.ProyectoDeFinanciamiento;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.persistence.DAOIngreso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngresoService {

    @Autowired
    private DAOIngreso dao;

    public List<Ingreso> getIngresos() {
        return dao.findAll();
    }

    public Ingreso find(int ingreso_id) {
        return dao.find(ingreso_id);
    }

    public void persist(List<Ingreso> ingresos) {
        ingresos.stream().forEach(i -> dao.persist(i));
    }

    public void persist(Ingreso ingreso) {
        dao.persist(ingreso);
    }

    public void updateDoc(Ingreso ingreso) {
        dao.updateDoc(ingreso);
    }

    public void updateSingle(Ingreso ingreso) {
        dao.update(ingreso);
    }

    public void update(List<Ingreso> ingresos) {
        for(Ingreso i: ingresos)
            updateSingle(i);
    }

    public List<Ingreso> getIngresosSinProyecto() {
        return dao.findAllSinProyecto();
    }

    public List<Ingreso> findIngresosDeProyecto(ProyectoDeFinanciamiento proyectoDeFinanciamiento) {
        return dao.findAllConProyecto().stream().filter(i -> i.getProyectoAsociado() == proyectoDeFinanciamiento)
                .collect(Collectors.toList());
    }

    public Double getTotalIngresos(Entidad entidad) {
        return dao.findAllConEgresos().stream().filter(i -> i.getEntidad().getId() == entidad.getId())
                .mapToDouble(Ingreso::getMonto).sum();
    }

    public Double getTotalIngresosEsteMes(Entidad entidad) {
        return dao.findAllConEgresos().stream()
                .filter(i -> i.getFechaIngreso().getMonthValue() == LocalDate.now().getMonthValue()
                        && i.getEntidad().getId() == entidad.getId())
                .mapToDouble(Ingreso::getMonto).sum();
    }

    public Ingreso findUltimoIngreso(Entidad entidad) {
        return dao.findAllConEgresos().stream().reduce((first, second) -> second)
                .filter(i -> i.getEntidad().getId() == entidad.getId()).orElse(null);
    }

    public List<Ingreso> getIngresosDisponibles(Entidad entidad) {
        return dao.findAllConEgresos().stream()
                .filter(i -> i.valorDisponible() > 0 && i.getEntidad().getId() == entidad.getId())
                .collect(Collectors.toList());
    }

    public List<Ingreso> getIngresos(Entidad entidad) {
        return dao.findAll().stream().filter(i -> i.getEntidad().getId() == entidad.getId())
                .collect(Collectors.toList());
    }
}
