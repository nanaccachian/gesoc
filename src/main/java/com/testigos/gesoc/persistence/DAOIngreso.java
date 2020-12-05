package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Ingreso> findAllConProyecto() {
        createEntityManager();
        beginTransaction();
        List<Ingreso> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for(Ingreso i: tList)
            Hibernate.initialize(i.getProyectoAsociado());
        commit();
        close();
        return tList;
    }

    public List<Ingreso> findAllSinProyecto() {
        createEntityManager();
        beginTransaction();
        List<Ingreso> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for(Ingreso i: tList)
            Hibernate.initialize(i.getProyectoAsociado());
        commit();
        close();
        return tList.stream().filter(i -> i.getProyectoAsociado() == null).collect(Collectors.toList());
    }

//    public void updateProyecto(Ingreso i,ProyectoDeFinanciamiento proyecto) {
//        createEntityManager();
//        beginTransaction();
//        ProyectoDeFinanciamiento p = em.find(ProyectoDeFinanciamiento.class,proyecto.getId());
//        i.setProyectoAsociado(p);
//        em.merge(i);
//        commit();
//        close();
//    }

    public void update2(Ingreso i) {
        createEntityManager();
        beginTransaction();
        Query q = em.createQuery("UPDATE Ingreso i set i.proyectoAsociado = :proy where i.id = :id");
        q.setParameter("proy",i.getProyectoAsociado());
        q.setParameter("id",i.getId());
        q.executeUpdate();
        em.getTransaction().commit();
        close();
    }

//    public void mergeConProyecto(ProyectoDeFinanciamiento proyecto, Ingreso ingreso) {
//        createEntityManager();
//        beginTransaction();
//        Egreso eg = em.merge(egreso);
//        item.setEgreso(egreso);
//        commit();
//        close();
//    }


//    public void update(Ingreso ingreso) {
//        createEntityManager();
//        beginTransaction();
//        em.createQuery("UPDATE Ingreso i set where ");
//        commit();
//        close();
//    }

}
