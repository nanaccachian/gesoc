package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.entidades.Entidad;
import org.springframework.stereotype.Repository;

@Repository
public class DAOEntidad extends DAO<Entidad>{

    public DAOEntidad() {
        super(Entidad.class);
    }

    public Entidad findConTipo(int id) {
        createEntityManager();
        beginTransaction();
        Entidad t = em.find(type, id);
        if (t != null) {
            t.getClass();
            t.getTipo();
        }
        commit();
        close();
        return t;
    }
}
