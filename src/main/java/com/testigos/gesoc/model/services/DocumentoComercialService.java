package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.DocumentoComercial;
import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.persistence.DAODocumentoComercial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoComercialService {

    @Autowired
    private DAODocumentoComercial repo;

    public void persist(DocumentoComercial documentoComercial, Egreso egreso) {
        repo.persistConEgreso(documentoComercial,egreso);
    }
}
