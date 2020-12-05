package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.model.domain.financiamiento.ProyectoDeFinanciamiento;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProyectoDeFinanciamientoAspect {
    @Autowired
    private RegistroRepository repo;

    @AfterReturning("execution(* com.testigos.gesoc.model.services.ProyectoDeFinanciamientoService.persist(..))")
    public void registerPersist(JoinPoint joinPoint) {
        ProyectoDeFinanciamiento proyectoDeFinanciamiento = (ProyectoDeFinanciamiento) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.ALTA, ProyectoDeFinanciamiento.class.getSimpleName(), "Se inserto un proyecto con id: " + proyectoDeFinanciamiento.getId() + ", por el monto de: "+ proyectoDeFinanciamiento.getMontoTotal()));
    }

    @AfterReturning("execution(* com.testigos.gesoc.model.services.ProyectoDeFinanciamientoService.update(..))")
    public void registerUpdate(JoinPoint joinPoint) {
        ProyectoDeFinanciamiento proyectoDeFinanciamiento = (ProyectoDeFinanciamiento) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.MODIFICACION, ProyectoDeFinanciamiento.class.getSimpleName(), "Se modifico el proyecto con id: " + proyectoDeFinanciamiento.getId() + ", descripcion: "+ proyectoDeFinanciamiento.getDescripcion()));
    }
}
