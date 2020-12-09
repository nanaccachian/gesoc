package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;
import org.springframework.stereotype.Repository;

@Repository
public class DAOItem extends DAO<Item>{

    public DAOItem() {
        super(Item.class);
    }

    public void persistWithEgreso(Item item, Egreso egreso) {
        createEntityManager();
        beginTransaction();
        Egreso egreso1 = em.find(Egreso.class,egreso.getId());
        if(egreso1 == null)
            egreso1 = em.find(EgresoConPresupuestos.class,egreso.getId());
        item.setEgreso(egreso1);
        em.persist(item);
        commit();
        close();
    }

    public void persistWithPresupuesto(Item item, Presupuesto presupuesto) {
        createEntityManager();
        beginTransaction();
        Presupuesto p = em.find(Presupuesto.class,presupuesto.getId());
        item.setPresupuesto(p);
        em.persist(item);
        commit();
        close();
    }
}
