package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.entidades.Entidad;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

@Repository
public class DAOUsuario extends DAO<Usuario>{

    public DAOUsuario() {
        super(Usuario.class);

    }

    public Usuario findConEntidad(String username) {
        createEntityManager();
        beginTransaction();
        Usuario t = em.find(type, username);
        if (t != null) {
            t.getClass();
            Hibernate.initialize(t.getEntidad());
        }
        commit();
        close();
        return t;
    }
}
