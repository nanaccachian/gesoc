package com.testigos.gesoc.model.services;

import java.util.List;
import java.util.stream.Collectors;

import com.testigos.gesoc.model.domain.entidades.Entidad;
import com.testigos.gesoc.model.domain.usuarios.Usuario;

import com.testigos.gesoc.persistence.DAOUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private DAOUsuario repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public List<Usuario> findAll(Entidad entidad) {
        return repository.findAllConEntidad().stream().filter(u -> u.getEntidad().getId() == entidad.getId()).collect(Collectors.toList());
    }

    public Usuario find(String id) {
        return repository.find(id);
    }

    public Usuario findConEntidad(String id) {
        return repository.findConEntidad(id);
    }

    public void persist(Usuario usuario) {
        repository.persist(usuario);
    }

    public Usuario findConEntidadYManejador(String name) {
        return repository.findConEntidadYManejador(name);
    }
}
