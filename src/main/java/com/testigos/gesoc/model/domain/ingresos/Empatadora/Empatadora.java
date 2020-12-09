package com.testigos.gesoc.model.domain.ingresos.Empatadora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class Empatadora {

    private static Empatadora empatadora = new Empatadora();

    private static @Getter List<EstrategiaEmpatadora> estrategias = Arrays.asList(new PrimeroEgreso(), new PrimeroIngreso(), new PorFecha());

    private static @Getter @Setter EstrategiaEmpatadora estrategiaElegida;

    private List<Condicion> condiciones;

    public List<Egreso> empatar(List<Ingreso> ingresos, List<Egreso> egresos) {
        if (ingresos != null && egresos != null)
            return estrategiaElegida.empatar(condiciones, ingresos, egresos);
        else return new ArrayList<>();
    }

    public static Empatadora getInstance() {
        return empatadora;
    }

    public EstrategiaEmpatadora getEstrategia(String estrategia) {
        return estrategias.stream().filter(e -> e.getClass().getSimpleName().equals(estrategia)).findFirst().get();
    }
}
