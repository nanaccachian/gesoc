package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UsuarioAspect {

    @Autowired
    private RegistroRepository repo;

    @Pointcut("execution(* com.testigos.gesoc.persistence.DAO.persist(..))")
    public void persist() { }

    @After("persist()")
    public void registerPersist(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        repo.save(new Registro(TipoRegistro.ALTA, args[0].getClass().toString(), "Se inserto " + args[0].toString()));
    }
}
