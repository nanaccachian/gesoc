package com.testigos.gesoc.model.services;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Categoria;
import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.Item;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;
import com.testigos.gesoc.persistence.DAOItem;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private DAOItem repo;

    public List<Item> findItems(Egreso egreso) {
        return repo.findItems(egreso);
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

    public Item find(int item_elegido) {
        return repo.find(item_elegido);
    }

    public Item findConCategorias(int item_id) {
        return repo.findConCategorias(item_id);
    }

    public void update(Item item) {
        repo.update(item);
    }

    public List<Item> findItemsSegunCategoria(int categoria_elegida) {
        return repo.findItemsSegunCategoria(categoria_elegida);
    }
}
