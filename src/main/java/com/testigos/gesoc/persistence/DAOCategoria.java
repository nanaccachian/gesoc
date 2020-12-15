package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.Categoria;
import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.ManejadorDeCategorias;
import com.testigos.gesoc.model.domain.entidades.Entidad;
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
        commit();
        close();
        return cat;
    }

    public List<Categoria> findCategoriasDeManejador(ManejadorDeCategorias e) {
        createEntityManager();
        beginTransaction();
        List<Categoria> cat = em.createQuery("SELECT cat FROM Categoria cat JOIN FETCH cat.criterio where cat.criterio.manejadorDeCategorias.id = :id").setParameter("id",e.getId()).getResultList();
        commit();
        close();
        return cat;
    }
}
