package com.testigos.gesoc.persistence;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

    @Query("SELECT user FROM Usuario user JOIN FETCH user.entidad WHERE user.username = (:username)")
    Usuario findConEntidad(@Param("username") String username);

//    @Query("SELECT user FROM Usuario user WHERE user.username = (:username)")
    Usuario findByUsername(String username);

}
