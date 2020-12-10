package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Item> findItems(Egreso egreso) {
        createEntityManager();
        beginTransaction();
        List<Item> tList = em.createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null) {
            tList.size();
            for (Item i : tList) {
                Hibernate.initialize(i.getEgreso());
                Hibernate.initialize(i.getCategorizacion());
            }
            tList = tList.stream().filter(i -> i.getEgreso() != null && i.getEgreso().getId() == egreso.getId()).collect(Collectors.toList());
        }
        commit();
        close();
        return tList;
    }


}
