package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.egresos.DocumentoComercial;
import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import org.springframework.stereotype.Repository;

@Repository
public class DAODocumentoComercial extends DAO<DocumentoComercial> {

    public DAODocumentoComercial() {
        super(DocumentoComercial.class);
    }

    public void persistConEgreso(DocumentoComercial documentoComercial, Egreso egreso) {
        createEntityManager();
        beginTransaction();
        Egreso egreso1 = em.find(Egreso.class,egreso.getId());
        if (egreso1 == null)
            egreso1 = em.find(EgresoConPresupuestos.class,egreso.getId());
        egreso1.setDocumento(documentoComercial);
        em.persist(documentoComercial);
        em.flush();
        commit();
        close();
    }
}
