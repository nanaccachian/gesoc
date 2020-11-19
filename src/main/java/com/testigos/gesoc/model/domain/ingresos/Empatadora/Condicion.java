package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;

public abstract class Condicion {
    public boolean cumpleCondicion(Ingreso ing, Egreso eg) {
        return true;
    }
}
