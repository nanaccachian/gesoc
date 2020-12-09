package com.testigos.gesoc.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

@Repository
public class DAOEgresoCP extends DAO<EgresoConPresupuestos> {

    public DAOEgresoCP() {
        super(EgresoConPresupuestos.class);
    }

    public EgresoConPresupuestos findConPresupuestos(int id) {
        createEntityManager();
        beginTransaction();
        EgresoConPresupuestos eg = em.find(type, id);
        if (eg != null) {
            Hibernate.initialize(eg.getTodosLosPresupuestos());
            List<Presupuesto> ps = eg.getTodosLosPresupuestos();
            for (Presupuesto p : ps)
                Hibernate.initialize(p.getItems());

            if (eg.getPresupuestoElegido() != null) {
                Hibernate.initialize(eg.getPresupuestoElegido());
                Hibernate.initialize(eg.getPresupuestoElegido().getItems());
            }
        }
        commit();
        close();
        return eg;
    }

    public void updatePresupuesto(EgresoConPresupuestos e) {
        createEntityManager();
        beginTransaction();
        Query q = em.createQuery("UPDATE EgresoConPresupuestos e set e.presupuestoElegido = :i where e.id = :id");
        q.setParameter("i", e.getPresupuestoElegido());
        q.setParameter("id", e.getId());
        q.executeUpdate();
        em.getTransaction().commit();
        close();
    }

    public List<EgresoConPresupuestos> findAllConProveedor() {
        createEntityManager();
        beginTransaction();
        List<EgresoConPresupuestos> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for (Egreso e : tList) {
            Hibernate.initialize(e.getVendedor());
            Hibernate.initialize(e.getItems());
        }
        commit();
        close();
        return tList;
    }

    public List<EgresoConPresupuestos> findAllConDocumentoComercial() {
        createEntityManager();
        beginTransaction();
        List<EgresoConPresupuestos> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for (EgresoConPresupuestos e : tList)
            Hibernate.initialize(e.getDocumento());
        commit();
        close();
        return tList;
    }


    public List<EgresoConPresupuestos> findAllConUsuario() {

        createEntityManager();
        beginTransaction();
        List<EgresoConPresupuestos> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for (EgresoConPresupuestos eg : tList) {
            Hibernate.initialize(eg.getRevisor());

            Hibernate.initialize(eg.getTodosLosPresupuestos());

            List<Presupuesto> ps = eg.getTodosLosPresupuestos();
            for (Presupuesto p : ps) {
                Hibernate.initialize(p.getItems());
                Hibernate.initialize(p.getEgresoConPresupuestos());
                Hibernate.initialize(p.getEgresoConPresupuestos().getItems());
            }

            if (eg.getPresupuestoElegido() != null) {
                Hibernate.initialize(eg.getPresupuestoElegido());
                Hibernate.initialize(eg.getPresupuestoElegido().getItems());
                Hibernate.initialize(eg.getPresupuestoElegido().getEgresoConPresupuestos());
                Hibernate.initialize(eg.getPresupuestoElegido().getEgresoConPresupuestos().getItems());
            }
        }

        commit();
        close();
        return tList;
    }

    public List<EgresoConPresupuestos> findAllConItems() {
        createEntityManager();
        beginTransaction();
        List<EgresoConPresupuestos> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();

        for (EgresoConPresupuestos e : tList)
            Hibernate.initialize(e.getItems());
        commit();
        close();
        return tList;
    }

    public List<EgresoConPresupuestos> findAllConProveedoreItems() {
        createEntityManager();
        beginTransaction();
        List<EgresoConPresupuestos> tList = createQuery("From " + type.getSimpleName()).getResultList();
        if (tList != null)
            tList.size();
        for (EgresoConPresupuestos e : tList) {
            Hibernate.initialize(e.getItems());
            Hibernate.initialize(e.getVendedor());
        }
        commit();
        close();
        return tList;
    }
}
