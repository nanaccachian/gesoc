package com.testigos.gesoc.model.services;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;
import com.testigos.gesoc.persistence.DAOItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private DAOItem repo;

    public List<Item> findItems(Egreso egreso) {
        repo.createEntityManager();
        repo.beginTransaction();
        List<Item> items = repo.createQuery("from Item m where m.egreso = :egreso").setParameter("egreso", egreso)
                .getResultList();
        if (items != null)
            items.size();
        repo.commit();
        repo.close();
        return items;
    }

    public void persist(Item item) {
        repo.persist(item);
    }

    public void persist(Item item, Egreso egreso) {
        repo.persistWithEgreso(item, egreso);
    }

    public void persist(Item item, Presupuesto presupuesto) {
        repo.persistWithPresupuesto(item, presupuesto);
    }
}
