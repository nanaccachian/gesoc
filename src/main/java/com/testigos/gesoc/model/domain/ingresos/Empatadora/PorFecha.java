package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.Comparator;
import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;

public class PorFecha extends EstrategiaEmpatadora {

    public void empatar(List<Condicion> condiciones, List<Ingreso> ingresos, List<Egreso> egresos) {

        egresos.sort(Comparator.comparing(Egreso::getFechaOperacion));

        for (Ingreso ing : ingresos) {
            int i = 0;
            double valor = ing.getValorTotal();
            for (Egreso eg : egresos) {
                if (valor > eg.valorTotal() && condiciones.stream().allMatch(cond -> cond.cumpleCondicion(ing, eg))) {
                    ing.getEgresosAsociados().add(eg);
                    eg.setIngresoAsociado(ing);
                    valor -= eg.valorTotal();
                    egresos.remove(eg);
                    i++;
                } else
                    break;
            }
            for (int j = 0; j < i; j++)
                egresos.remove(j);
        }
    }
}