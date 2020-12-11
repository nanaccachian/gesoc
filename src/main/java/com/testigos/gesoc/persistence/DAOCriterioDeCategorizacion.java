package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.CriterioDeCategorizacion;
import com.testigos.gesoc.model.domain.egresos.ManejadorDeCategorias;
import com.testigos.gesoc.model.domain.entidades.Entidad;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOCriterioDeCategorizacion extends DAO<CriterioDeCategorizacion> {

    public DAOCriterioDeCategorizacion() { super(CriterioDeCategorizacion.class);}

    public CriterioDeCategorizacion findConCategorias(int criterio_elegido) {
        createEntityManager();
        beginTransaction();
        CriterioDeCategorizacion t = em.find(type, criterio_elegido);
        if (t != null) {
            t.getClass();
            Hibernate.initialize(t.getCategorias());
        }
        commit();
        close();
        return t;
    }
}
