package com.testigos.gesoc.model.services.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RegistroAspect {

    // @Autowired
    // private RegistroRepository repo;
    //
    // @AfterReturning("execution(*
    // com.testigos.gesoc.model.services.DomainServices.*Service.persist(..))")
    // public void registerPersist(JoinPoint joinPoint) {
    // Object[] args = joinPoint.getArgs();
    // repo.save(new Registro(TipoRegistro.ALTA, args[0].getClass().getSimpleName(),
    // "Se inserto " + args[0].toString()));
    // }
    //
    // @AfterReturning("execution(*
    // com.testigos.gesoc.model.services.DomainServices.*Service.update(..))")
    // public void registerUpdate(JoinPoint joinPoint) {
    // Object[] args = joinPoint.getArgs();
    // repo.save(new Registro(TipoRegistro.MODIFICACION,
    // args[0].getClass().getSimpleName(), "Se inserto " + args[0].toString()));
    // }
    //
    // @AfterReturning("execution(*
    // com.testigos.gesoc.model.services.DomainServices.*Service.delete(..))")
    // public void registerDelete(JoinPoint joinPoint) {
    // Object[] args = joinPoint.getArgs();
    // repo.save(new Registro(TipoRegistro.BAJA, args[0].getClass().getSimpleName(),
    // "Se inserto " + args[0].toString()));
    // }
}
