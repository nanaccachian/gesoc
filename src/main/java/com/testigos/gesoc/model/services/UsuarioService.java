package com.testigos.gesoc.model.services;

import java.util.List;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.persistence.DAO;

import com.testigos.gesoc.persistence.DAOUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

//    @Autowired
    private DAOUsuario dao = new DAOUsuario();

    public List<Usuario> findAll() {
        return dao.findAll();
    }

    public Usuario find(String id) {
        return dao.find(id);
    }

    public Usuario findConEntidad(String id) {
        return dao.findConEntidad(id);
    }

    public void persist(Usuario usuario) {
        dao.persist(usuario);
    }
}
