package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.Proveedor;
import com.testigos.gesoc.persistence.DAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final DAO<Proveedor> dao = new DAO<>(Proveedor.class);

    public List<Proveedor> findAll() {
        return dao.findAll();
    }

    public Proveedor find(int id) {
        return dao.find(id);
    }
}
