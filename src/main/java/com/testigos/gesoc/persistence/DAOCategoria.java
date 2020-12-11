package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.Categoria;
import com.testigos.gesoc.model.domain.egresos.Egreso;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOCategoria extends DAO<Categoria> {
    
    public DAOCategoria() {
        super(Categoria.class);
    }

    public List<Categoria> findAll() {
        createEntityManager();
        beginTransaction();
        List<Categoria> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for (Categoria e : tList)
            Hibernate.initialize(e.getCriterio());
        commit();
        close();
        return tList;
    }

    public Categoria findConCriterio(int categoria_elegida) {
        createEntityManager();
        beginTransaction();
        Categoria cat = (Categoria) em.createQuery("SELECT cat FROM Categoria cat JOIN FETCH cat.criterio WHERE cat.id = :id").setParameter("id",categoria_elegida).getSingleResult();
//        Categoria cat = em.find(type,categoria_elegida);
//        if (cat != null) {
//            cat.getClass();
//            Hibernate.initialize(cat.getCriterio());
//            Hibernate.unproxy(cat.getCriterio());
//        }
        commit();
        close();
        return cat;
    }
}
