package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.ArrayList;
import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Mix extends EstrategiaEmpatadora {

    private List<EstrategiaEmpatadora> estrategias;

    public List<Egreso> empatar(List<Condicion> condiciones, List<Ingreso> ingresos, List<Egreso> egresos) {

        List<Egreso> egresosADevolver = new ArrayList<>();

        for (EstrategiaEmpatadora est : estrategias) {
            List<Egreso> egresosRecursivos = est.empatar(condiciones, ingresos, egresos);
            egresosADevolver.addAll(egresosRecursivos);
            egresos.removeAll(egresosADevolver);
        }

        return egresosADevolver;
    }
}
