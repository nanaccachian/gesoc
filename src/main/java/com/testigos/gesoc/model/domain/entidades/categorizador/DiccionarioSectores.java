package com.testigos.gesoc.model.domain.entidades.categorizador;

import static com.testigos.gesoc.model.domain.entidades.categorizador.SectoresEnum.AGROPECUARIO;
import static com.testigos.gesoc.model.domain.entidades.categorizador.SectoresEnum.COMERCIO;
import static com.testigos.gesoc.model.domain.entidades.categorizador.SectoresEnum.CONSTRUCCION;
import static com.testigos.gesoc.model.domain.entidades.categorizador.SectoresEnum.INDUSTRIAYMINERIA;
import static com.testigos.gesoc.model.domain.entidades.categorizador.SectoresEnum.SERVICIOS;
import static com.testigos.gesoc.model.domain.entidades.categorizador.TamaniosEnum.MEDIANATRAMO1;
import static com.testigos.gesoc.model.domain.entidades.categorizador.TamaniosEnum.MEDIANATRAMO2;
import static com.testigos.gesoc.model.domain.entidades.categorizador.TamaniosEnum.MICRO;
import static com.testigos.gesoc.model.domain.entidades.categorizador.TamaniosEnum.PEQUENIA;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class DiccionarioSectores {

    private static final Table<SectoresEnum, TamaniosEnum, double[]> dictionary = HashBasedTable.create();

    public DiccionarioSectores() {
        inicializarDiccionario();
    }

    public double getPromVentas(SectoresEnum s, TamaniosEnum t) {
        if (dictionary.get(s, t) == null)
            throw new RuntimeException("No esta en diccionario");
        return dictionary.get(s, t)[0];
    }

    public double getPersonal(SectoresEnum s, TamaniosEnum t) {
        if (dictionary.get(s, t) == null)
            throw new RuntimeException("No esta en diccionario");
        return dictionary.get(s, t)[1];
    }

    public void inicializarDiccionario() {

        dictionary.put(AGROPECUARIO, MICRO, new double[] { 12890000, 5 });
        dictionary.put(AGROPECUARIO, PEQUENIA, new double[] { 48480000, 10 });
        dictionary.put(AGROPECUARIO, MEDIANATRAMO1, new double[] { 345430000, 50 });
        dictionary.put(AGROPECUARIO, MEDIANATRAMO2, new double[] { 547890000, 215 });

        dictionary.put(INDUSTRIAYMINERIA, MICRO, new double[] { 26540000, 15 });
        dictionary.put(INDUSTRIAYMINERIA, PEQUENIA, new double[] { 190410000, 60 });
        dictionary.put(INDUSTRIAYMINERIA, MEDIANATRAMO1, new double[] { 1190330000, 235 });
        dictionary.put(INDUSTRIAYMINERIA, MEDIANATRAMO2, new double[] { 1739590000, 655 });

        dictionary.put(SERVICIOS, MICRO, new double[] { 8500000, 7 });
        dictionary.put(SERVICIOS, PEQUENIA, new double[] { 50950000, 30 });
        dictionary.put(SERVICIOS, MEDIANATRAMO1, new double[] { 425170000, 165 });
        dictionary.put(SERVICIOS, MEDIANATRAMO2, new double[] { 607210000, 535 });

        dictionary.put(COMERCIO, MICRO, new double[] { 29740000, 7 });
        dictionary.put(COMERCIO, PEQUENIA, new double[] { 178860000, 35 });
        dictionary.put(COMERCIO, MEDIANATRAMO1, new double[] { 1502750000, 125 });
        dictionary.put(COMERCIO, MEDIANATRAMO2, new double[] { 2146890000, 345 });

        dictionary.put(CONSTRUCCION, MICRO, new double[] { 15230000, 12 });
        dictionary.put(CONSTRUCCION, PEQUENIA, new double[] { 90310000, 45 });
        dictionary.put(CONSTRUCCION, MEDIANATRAMO1, new double[] { 503880000, 200 });
        dictionary.put(CONSTRUCCION, MEDIANATRAMO2, new double[] { 755740000, 590 });
    }
}
