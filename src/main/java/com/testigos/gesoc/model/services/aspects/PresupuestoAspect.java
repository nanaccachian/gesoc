package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PresupuestoAspect {

    @Autowired
    private RegistroRepository repo;

    @AfterReturning("execution(* com.testigos.gesoc.model.services.PresupuestoService.persist(..))")
    public void registerPersist(JoinPoint joinPoint) {
        Presupuesto p = (Presupuesto) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.ALTA, Presupuesto.class.getSimpleName(),
                "Se inserto un presupuesto: " + p.getDescripcion() + ", referido al egreso: " + p.getEgresoConPresupuestos().getDescripcion()));
    }
}