package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;

public class Empatadora {

    private static EstrategiaEmpatadora estrategiaEmpatadora;

    private static List<Condicion> condiciones;

    public static void empatar(List<Ingreso> ingresos, List<Egreso> egresos) {
        estrategiaEmpatadora.empatar(condiciones, ingresos, egresos);
    }
}
