package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UsuarioAspect {

    @Autowired
    private RegistroRepository repo;

    @AfterReturning("execution(* com.testigos.gesoc.model.services.UsuarioService.persist(..))")
    public void registerPersist(JoinPoint joinPoint) {
        Usuario user = (Usuario) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.ALTA, Usuario.class.getSimpleName(), "Se agrego como usuario a: " + user.getName() + " " + user.getSurname()));
    }

//    @AfterReturning("execution(* com.testigos.gesoc.model.services.UsuarioService.update(..))")
//    public void registerUpdate(JoinPoint joinPoint) {
//        Usuario user = (Usuario) joinPoint.getArgs()[0];
//        repo.save(new Registro(TipoRegistro.MODIFICACION, Usuario.class.getSimpleName(), "Se modifico al usuario: " + user.getName() + " " + user.getSurname()));
//    }
}