package com.testigos.gesoc.model.services.budgetValidator;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import com.testigos.gesoc.model.domain.egresos.Presupuesto;
import com.testigos.gesoc.model.domain.usuarios.Mensaje;
import com.testigos.gesoc.model.services.EgresoService;
import com.testigos.gesoc.model.services.MensajeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPresupuestos {

    @Autowired
    private EgresoService egresoService;

    @Autowired
    private MensajeService mensajeService;

    public static boolean validar(EgresoConPresupuestos egresoConPresupuestos) {
        return validarCantPresupuestos(egresoConPresupuestos) && validarCompraEnBaseAPresupuestos(egresoConPresupuestos)
                && validarCriterioCorrecto(egresoConPresupuestos);
    }

    public static boolean validarCantPresupuestos(EgresoConPresupuestos egresoConPresupuestos) {
        return (egresoConPresupuestos.getTodosLosPresupuestos().size() == egresoConPresupuestos.getComprador().getCantidadPresupuestosRequeridos());
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

    @Async
    @Scheduled(cron = "0 0 9 * * *")
    public void execute() {
        List<EgresoConPresupuestos> egs =  egresoService.getEgresosInvalidosConUsuario();

        for (EgresoConPresupuestos eg : egs) {
            if (eg.esValido()) {
                eg.setEsValidoElPresupuesto(true);
                egresoService.updateEgreso(eg);
            } else
                mensajeService.persist(new Mensaje(eg.getRevisor(), "El egreso: " + eg.getId() +  " no es válido", ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        }
    }
}
