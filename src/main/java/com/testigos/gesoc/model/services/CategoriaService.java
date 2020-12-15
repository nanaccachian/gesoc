package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.egresos.Categoria;
import com.testigos.gesoc.model.domain.egresos.ManejadorDeCategorias;
import com.testigos.gesoc.persistence.DAOCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriaService {

    @Autowired
    private DAOCategoria repo;

    public Categoria findConCriterio(int categoria_elegida) {
        return repo.findConCriterio(categoria_elegida);
    }

    public List<Categoria> findCategoriasDeManejador(ManejadorDeCategorias manejadorDeCategorias) {
        return repo.findCategoriasDeManejador(manejadorDeCategorias);
    }
}
