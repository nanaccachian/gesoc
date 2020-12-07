package com.testigos.gesoc.model.services;

import com.testigos.gesoc.persistence.DAOEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntidadService {

    @Autowired
    private DAOEntidad repo;
}
