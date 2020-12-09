package com.testigos.gesoc.model.services.aspects;

import com.testigos.gesoc.model.domain.abm.Registro;
import com.testigos.gesoc.model.domain.abm.TipoRegistro;
import com.testigos.gesoc.model.domain.egresos.DocumentoComercial;
import com.testigos.gesoc.persistence.MongoRepositories.RegistroRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class DocumentoComercialAspect {

    @Autowired
    private RegistroRepository repo;

    @AfterReturning("execution(* com.testigos.gesoc.model.services.DocumentoComercialService.persist(..))")
    public void registerPersist(JoinPoint joinPoint) {
        DocumentoComercial doc = (DocumentoComercial) joinPoint.getArgs()[0];
        repo.save(new Registro(TipoRegistro.ALTA, DocumentoComercial.class.getSimpleName(),
                "Se inserto un/a " + doc.getTipo() + " N.º" + doc.getNum()));
    }

}