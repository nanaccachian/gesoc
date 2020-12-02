package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class DAOEgreso extends DAO<Egreso> {

    public DAOEgreso() {
        super(Egreso.class);
    }

    public void update(Egreso e) {
        createEntityManager();
        beginTransaction();
        Query q = em.createQuery("UPDATE Egreso e set e.ingresoAsociado = :i where e.id = :id");
        q.setParameter("i",e.getIngresoAsociado());
        q.setParameter("id",e.getId());
        q.executeUpdate();
        em.getTransaction().commit();
        close();
    }
}
