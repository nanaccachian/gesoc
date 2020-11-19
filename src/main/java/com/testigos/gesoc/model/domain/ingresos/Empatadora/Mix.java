package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Mix extends EstrategiaEmpatadora {

    private List<EstrategiaEmpatadora> estrategias;

    public void empatar(List<Condicion> condiciones, List<Ingreso> ingresos, List<Egreso> egresos) {
        for (EstrategiaEmpatadora est : estrategias)
            est.empatar(condiciones, ingresos, egresos);
    }
}
