package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.CriterioDeCategorizacion;
import com.testigos.gesoc.persistence.DAOCriterioDeCategorizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CriterioDeCategorizacionService {

    @Autowired
    public DAOCriterioDeCategorizacion repo;

    public CriterioDeCategorizacion findConCategorias(int criterio_elegido) {
        return repo.findConCategorias(criterio_elegido);
    }
}
