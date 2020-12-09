package com.testigos.gesoc.model.services.budgetValidator;

import java.util.Optional;

import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.services.EgresoService;
import com.testigos.gesoc.model.services.MensajeService;
import com.testigos.gesoc.model.services.UsuarioService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.NoArgsConstructor;
import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ValidadorPresupuestos implements Job {

    @Autowired
    private EgresoService egresoCPService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MensajeService mensajeService;

    public static boolean validar(EgresoConPresupuestos egresoConPresupuestos) {
        return validarCantPresupuestos(egresoConPresupuestos) && validarCompraEnBaseAPresupuestos(egresoConPresupuestos)
                && validarCriterioCorrecto(egresoConPresupuestos);
    }

    public static boolean validarCantPresupuestos(EgresoConPresupuestos egresoConPresupuestos) {
        return (egresoConPresupuestos.getTodosLosPresupuestos().size() == 3);
    }

    public static boolean validarCompraEnBaseAPresupuestos(EgresoConPresupuestos egresoConPresupuestos) {
        return (egresoConPresupuestos.getTodosLosPresupuestos()
                .contains(egresoConPresupuestos.getPresupuestoElegido()));
    }

    public static boolean validarCriterioCorrecto(EgresoConPresupuestos egresoConPresupuestos) {
        Optional<Presupuesto> prep = Optional.ofNullable(egresoConPresupuestos.getPresupuestoElegido());
        return (prep.equals(egresoConPresupuestos.getCriterio()
                .presupuestoElegido(egresoConPresupuestos.getTodosLosPresupuestos())));
    }

    public static boolean itemsEnPresupuesto(Presupuesto presupuesto) {
        return presupuesto.getEgresoConPresupuestos().listaItems().containsAll(presupuesto.listaItems());
    }

     @Override
     public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
         for (EgresoConPresupuestos eg : egresoCPService.getEgresosInvalidosConUsuario()) {
             if (eg.esValido()) {
                 eg.setEsValidoElPresupuesto(true);
                 egresoCPService.persist(eg);
             } else
                 mensajeService.persist(new Mensaje(eg.getRevisor(), "El egreso no es válido: " + eg.toString()));
         }
     }
}
