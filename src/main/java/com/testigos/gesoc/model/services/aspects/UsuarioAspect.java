package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UsuarioAspect {

    @Autowired
    private RegistroRepository repo;

    @Pointcut("execution( * com.testigos.gesoc.persistence.DAO.persist(..))")
    public void persist() { }

    @AfterReturning("persist()")
    public void registerPersist(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        //repo.save(new Registro(TipoRegistro.ALTA, args[0].getClass().toString(),"Se inserto " + args[0].toString()));
        System.out.println(args[0].toString());
    }
}
