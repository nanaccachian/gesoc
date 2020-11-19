package com.testigos.gesoc.model.domain.egresos;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class MenorPresupuesto extends CriterioSeleccion {

    @Transient
    private static MenorPresupuesto instancia = null;

    @Override
    public Optional<Presupuesto> presupuestoElegido(List<Presupuesto> presupuestos) {
        return presupuestos.stream().min(MenorPresupuesto::comparar);
    }

    public static int comparar(Presupuesto p1, Presupuesto p2) {
        return Double.compare(p1.valorTotal(), p2.valorTotal());
    }

    private MenorPresupuesto() {
    }

    public static MenorPresupuesto getInstance() {
        if (instancia == null) {
            instancia = new MenorPresupuesto();
        }
        return instancia;
    }
}
