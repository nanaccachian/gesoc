package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;

public class CondicionPorFecha extends Condicion {
    private final int diasAceptibilidad;

    public CondicionPorFecha(int diasAceptibilidad) {
        this.diasAceptibilidad = diasAceptibilidad;
    }

    @Override
    public boolean cumpleCondicion(Ingreso ing, Egreso eg) {
        return ing.getFechaIngreso().plusDays(diasAceptibilidad).compareTo(eg.getFechaOperacion()) > 0;
    }
}
