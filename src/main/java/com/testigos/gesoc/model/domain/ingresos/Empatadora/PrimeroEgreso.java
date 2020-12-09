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
            for (Egreso eg : egresos) {
                if (ing.valorDisponible() >= eg.valorTotal() && (condiciones == null || condiciones.stream().allMatch(cond -> cond.cumpleCondicion(ing, eg)))) {
                    ing.addEgreso(eg);
                    eg.setIngresoAsociado(ing);
                    egresosADevolver.add(eg);
                    aEliminar.add(eg);
                } else break;
            }
            egresos.removeAll(aEliminar);
            aEliminar.clear();
        }

        return egresosADevolver;
    }
}
