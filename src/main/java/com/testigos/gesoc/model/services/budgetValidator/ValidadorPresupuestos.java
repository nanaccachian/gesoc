package com.testigos.gesoc.model.services.budgetValidator;

import java.util.Optional;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.NoArgsConstructor;
import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;

@NoArgsConstructor
public class ValidadorPresupuestos implements Job {

    // private final DAO<EgresoConPresupuestos> dao = new DAO<>();

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
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Auto-generated method stub

    }

    // @Override
    // public void execute(JobExecutionContext jobExecutionContext) {

    // for (EgresoConPresupuestos eg : dao.getEgresosInvalidos()) {
    // if (eg.esValido()) {
    // eg.setValidez(true);
    // dao.persist(eg);
    // } else {
    // for (Usuario user : eg.getRevisores())
    // dao.persist(new Mensaje(user, "El egreso no es válido: " + eg.toString()));
    // }
    // }
    // }
}
