package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DAOIngreso extends DAO<Ingreso>{

    public DAOIngreso() {
        super(Ingreso.class);
    }

    public List<Ingreso> findAllConEgresos() {
        createEntityManager();
        beginTransaction();
        List<Ingreso> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for(Ingreso i: tList)
            Hibernate.initialize(i.getEgresosAsociados());
        commit();
        close();
        return tList;
    }

//    public void update(Ingreso ingreso) {
//        createEntityManager();
//        beginTransaction();
//        em.createQuery("UPDATE Ingreso i set where ");
//        commit();
//        close();
//    }

}
