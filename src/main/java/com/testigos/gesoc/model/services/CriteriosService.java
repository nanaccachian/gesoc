package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.CriterioSeleccion;
import com.testigos.gesoc.persistence.DAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CriteriosService {

    DAO<CriterioSeleccion> dao = new DAO<>(CriterioSeleccion.class);

    public List<CriterioSeleccion> findAll() {
        return dao.findAll();
    }

    public CriterioSeleccion find(int parseInt) {
        return dao.find(parseInt);
    }
}
