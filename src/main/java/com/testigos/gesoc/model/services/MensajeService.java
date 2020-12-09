package com.testigos.gesoc.model.services;

import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.persistence.DAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajeService {
    private final DAO<Mensaje> dao = new DAO<>(Mensaje.class);

    public List<Mensaje> getMensajes(Usuario user) {
        dao.createEntityManager();
        dao.beginTransaction();
        List<Mensaje> mensajes = dao.createQuery("from Mensaje m where m.user = :user")
                .setParameter("user", user).getResultList();
        if (mensajes != null)
            mensajes.size();
        dao.commit();
        dao.close();
        return mensajes;
    }

    public void persist(Mensaje mensaje) {
        dao.persist(mensaje);
    }
}
