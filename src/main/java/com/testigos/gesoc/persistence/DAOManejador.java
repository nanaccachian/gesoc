package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.CriterioDeCategorizacion;
import com.testigos.gesoc.model.domain.egresos.ManejadorDeCategorias;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOManejador extends DAO<ManejadorDeCategorias> {

    public DAOManejador() { super(ManejadorDeCategorias.class);}

//    public List<CriterioDeCategorizacion> findCriterios(ManejadorDeCategorias manejadorDeCategorias) {
//        createEntityManager();
//        beginTransaction();
//        List<CriterioDeCategorizacion> tList = createQuery("From " + type.getSimpleName()).getResultList();
//        if (tList != null) {
//            tList.size();
//            for (CriterioDeCategorizacion e : tList)
//                Hibernate.initialize(e.get);
//        }
//
//        commit();
//        close();
//        return tList;
//    }
}
