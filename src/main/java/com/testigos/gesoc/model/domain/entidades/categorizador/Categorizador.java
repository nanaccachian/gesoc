package com.testigos.gesoc.model.domain.entidades.categorizador;

import static com.testigos.gesoc.model.domain.entidades.categorizador.TamaniosEnum.MEDIANATRAMO1;
import static com.testigos.gesoc.model.domain.entidades.categorizador.TamaniosEnum.MEDIANATRAMO2;
import static com.testigos.gesoc.model.domain.entidades.categorizador.TamaniosEnum.MICRO;
import static com.testigos.gesoc.model.domain.entidades.categorizador.TamaniosEnum.PEQUENIA;

import com.testigos.gesoc.model.domain.entidades.tipoorg.Empresa;
import com.testigos.gesoc.model.domain.entidades.tipoorg.MedianaTramo1;
import com.testigos.gesoc.model.domain.entidades.tipoorg.MedianaTramo2;
import com.testigos.gesoc.model.domain.entidades.tipoorg.Micro;
import com.testigos.gesoc.model.domain.entidades.tipoorg.Pequenia;
import com.testigos.gesoc.model.domain.entidades.tipoorg.TipoOrg;

public class Categorizador {

    private static final DiccionarioSectores diccionarioSectores = new DiccionarioSectores();

    private Categorizador() {
    }

    public static TipoOrg recategorizar(Empresa empresa) {
        if (puedeRecategorizarDe(empresa, MEDIANATRAMO2)) {
            throw new RuntimeException("No te podes recategorizar, sos OSC");
        } else if (puedeRecategorizarDe(empresa, MEDIANATRAMO1)) {
            return new MedianaTramo2(empresa.getActividad(), empresa.getSector(), empresa.getCantPersonal(),
                    empresa.getPromVentasAnuales());
        } else if (puedeRecategorizarDe(empresa, PEQUENIA)) {
            return new MedianaTramo1(empresa.getActividad(), empresa.getSector(), empresa.getCantPersonal(),
                    empresa.getPromVentasAnuales());
        } else if (puedeRecategorizarDe(empresa, MICRO)) {
            return new Pequenia(empresa.getActividad(), empresa.getSector(), empresa.getCantPersonal(),
                    empresa.getPromVentasAnuales());
        } else {
            return new Micro(empresa.getActividad(), empresa.getSector(), empresa.getCantPersonal(),
                    empresa.getPromVentasAnuales());
        }
    }

    private static boolean puedeRecategorizarDe(Empresa empresa, TamaniosEnum tamanio) {
        return empresa.getPromVentasAnuales() > diccionarioSectores.getPromVentas(empresa.getSector(), tamanio)
                || empresa.getCantPersonal() > diccionarioSectores.getPersonal(empresa.getSector(), tamanio);
    }
}