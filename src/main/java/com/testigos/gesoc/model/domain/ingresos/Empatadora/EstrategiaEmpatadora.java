package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;

public abstract class EstrategiaEmpatadora {

    public abstract List<Egreso> empatar(List<Condicion> condiciones, List<Ingreso> ingresos, List<Egreso> egresos);
}
