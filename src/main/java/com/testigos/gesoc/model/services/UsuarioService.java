package com.testigos.gesoc.model.services;

import java.util.List;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.persistence.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final DAO<Usuario> dao = new DAO<>(Usuario.class);

    public List<Usuario> findAll() {
        return dao.findAll();
    }

    public Usuario find(String id) {
        return dao.find(id);
    }

    public void persist(Usuario usuario) {
        dao.persist(usuario);
    }
}
