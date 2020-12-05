package com.testigos.gesoc.model.services;

import java.util.List;
import java.util.stream.Collectors;

import com.testigos.gesoc.model.domain.financiamiento.ProyectoDeFinanciamiento;
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

    public Ingreso find(int ingreso_id) {
        return dao.find(ingreso_id);
    }

    public List<Ingreso> getIngresosDisponibles() {
        return dao.findAllConEgresos().stream().filter(i -> i.valorDisponible() > 0).collect(Collectors.toList());
    }

    public List<Ingreso> getIngresosConProyecto() {
        return dao.findAllConProyecto();
    }

    public void persist(List<Ingreso> ingresos) {
        ingresos.stream().forEach(i -> dao.persist(i));
    }

    public void persist(Ingreso ingreso) {
        dao.persist(ingreso);
    }

    public void update2(Ingreso ingreso) {dao.update2(ingreso);}

    public List<Ingreso> getIngresosSinProyecto() {
        return dao.findAllSinProyecto();
    }

//    public void updateProyecto(Ingreso ingreso,ProyectoDeFinanciamiento proyectoDeFinanciamiento) {
//        dao.updateProyecto(ingreso,proyectoDeFinanciamiento);
//    }

    public List<Ingreso> findIngresosDeProyecto(ProyectoDeFinanciamiento proyectoDeFinanciamiento) {
        return dao.findAllConProyecto().stream().filter(i -> i.getProyectoAsociado() == proyectoDeFinanciamiento).collect(Collectors.toList());
    }
}
