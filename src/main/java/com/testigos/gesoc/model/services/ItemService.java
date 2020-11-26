package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.persistence.DAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private DAO<Item> repo = new DAO<>(Item.class);

    public List<Item> findItems(Egreso egreso) {
        repo.createEntityManager();
        repo.beginTransaction();
        List<Item> items = repo.createQuery("from Item m where m.egreso = :egreso")
                .setParameter("egreso", egreso).getResultList();
        if (items != null)
            items.size();
        repo.commit();
        repo.close();
        return items;
    }
}
