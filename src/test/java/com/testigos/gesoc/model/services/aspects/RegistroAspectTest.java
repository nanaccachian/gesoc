package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.entidades.JuridicaIGJ;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.persistence.DAO;

import org.junit.Test;

public class RegistroAspectTest {

    @Test
    public void persistUsuario() {
        DAO<Usuario> dao = new DAO<Usuario>(Usuario.class);

        Usuario user = new Usuario("username17", "password", "permisos", new JuridicaIGJ(), "name", "surname");

        dao.persist(user);
    }

    @Test
    public void removeUsuario() {
        DAO<Usuario> dao = new DAO<>(Usuario.class);

        Usuario user = dao.find("username");
        user.getClass();
        dao.remove(user);
    }
}
