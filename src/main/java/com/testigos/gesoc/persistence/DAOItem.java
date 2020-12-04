package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.Item;
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
        item.setEgreso(egreso1);
        em.persist(item);
        commit();
        close();
    }
}
