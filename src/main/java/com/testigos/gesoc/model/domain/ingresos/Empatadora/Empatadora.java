package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;

public class Empatadora {

    private Empatadora instancia = null;

    private EstrategiaEmpatadora estrategiaEmpatadora;

    private List<Condicion> condiciones;

    public void empatar(List<Ingreso> ingresos, List<Egreso> egresos) {
        estrategiaEmpatadora.empatar(condiciones, ingresos, egresos);
    }

    private Empatadora() {
    }

    public Empatadora getInstancia() {
        if (instancia == null)
            instancia = new Empatadora();
        return instancia;
    }
}
