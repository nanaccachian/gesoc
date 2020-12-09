package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;

public class PrimeroEgreso extends EstrategiaEmpatadora {

    public List<Egreso> empatar(List<Condicion> condiciones, List<Ingreso> ingresos, List<Egreso> egresos) {

        egresos.sort(Comparator.comparing(Egreso::valorTotal));

        List<Egreso> egresosADevolver = new ArrayList<>();
        List<Egreso> aEliminar = new ArrayList<>();

        for (Ingreso ing : ingresos) {
//            int i = 0;
            double valor = ing.getMonto();
            for (Egreso eg : egresos) {
                if (valor > eg.valorTotal() && (condiciones == null || condiciones.stream().allMatch(cond -> cond.cumpleCondicion(ing, eg)))) {
                    ing.getEgresosAsociados().add(eg);
                    eg.setIngresoAsociado(ing);
                    valor -= eg.valorTotal();
                    egresosADevolver.add(eg);
                    aEliminar.add(eg);
//                    i++;
                } else {
                    egresos.removeAll(aEliminar);
                    aEliminar.clear();
                    break;
                }
            }
//            for (int j = 0; j < i; j++)
//                egresos.remove(j);
        }

        return egresosADevolver;
    }
}
