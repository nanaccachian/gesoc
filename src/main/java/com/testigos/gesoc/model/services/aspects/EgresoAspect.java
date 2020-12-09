package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EgresoAspect {

    @Autowired
    private RegistroRepository repo;

    @AfterReturning("execution(* com.testigos.gesoc.model.services.EgresoService.persist(..))")
    public void registerPersist(JoinPoint joinPoint) {
        Egreso egreso = (Egreso) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.ALTA, Egreso.class.getSimpleName(),
                "Se inserto un egreso con id: " + egreso.getId() + ", referido a " + egreso.getDescripcion()));
    }

    @AfterReturning("execution(* com.testigos.gesoc.model.services.EgresoService.updateEgreso(..)) || execution(* com.testigos.gesoc.model.services.EgresoService.updatePresupuesto(..))")
    public void registerUpdate(JoinPoint joinPoint) {
        Egreso egreso = (Egreso) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.MODIFICACION, Egreso.class.getSimpleName(),
                "Se modifico el egreso con id: " + egreso.getId()));
    }
}