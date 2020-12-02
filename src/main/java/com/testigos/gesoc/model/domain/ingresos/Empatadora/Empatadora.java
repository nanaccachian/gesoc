package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

//TODO PONERLE UN PATRÓN LINDO
@Component
@NoArgsConstructor
public class Empatadora {

    private @Getter @Setter EstrategiaEmpatadora estrategiaEmpatadora;

    private List<Condicion> condiciones;

    public void empatar(List<Ingreso> ingresos, List<Egreso> egresos) {
        if(ingresos != null && egresos != null)
            estrategiaEmpatadora.empatar(condiciones, ingresos, egresos);
    }

    public Empatadora(EstrategiaEmpatadora estrategiaEmpatadora) {
        this.estrategiaEmpatadora = estrategiaEmpatadora;
    }


}
