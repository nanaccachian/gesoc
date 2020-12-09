package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class IngresoAspect {

    @Autowired
    private RegistroRepository repo;

    @AfterReturning("execution(* com.testigos.gesoc.model.services.IngresoService.persist(..))")
    public void registerPersist(JoinPoint joinPoint) {
        Ingreso ingreso = (Ingreso) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.ALTA, Ingreso.class.getSimpleName(), "Se inserto un ingreso con id: " + ingreso.getId() + ", por el monto de: "+ ingreso.getMonto()));
    }

    @AfterReturning("execution(* com.testigos.gesoc.model.services.IngresoService.updateDoc(..))")
    public void registerUpdate(JoinPoint joinPoint) {
        Ingreso ingreso = (Ingreso) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.MODIFICACION, Ingreso.class.getSimpleName(), "Se modifico el ingreso con id: " + ingreso.getId() + ", descripción: "+ ingreso.getDescripcion()));
    }
}