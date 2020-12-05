package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.financiamiento.ProyectoDeFinanciamiento;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOProyecto extends DAO<ProyectoDeFinanciamiento>{

    public DAOProyecto() {
        super(ProyectoDeFinanciamiento.class);
    }

    public List<ProyectoDeFinanciamiento> findAllConUsuario() {
        createEntityManager();
        beginTransaction();
        List<ProyectoDeFinanciamiento> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for(ProyectoDeFinanciamiento i: tList)
            Hibernate.initialize(i.getDirector());
        commit();
        close();
        return tList;
    }

    public ProyectoDeFinanciamiento findConIngresos(int proyecto_id) {
        createEntityManager();
        beginTransaction();
        ProyectoDeFinanciamiento project = em.find(type,proyecto_id);
        Hibernate.initialize(project.getIngresosAsociados());
        commit();
        close();
        return project;
    }
}
