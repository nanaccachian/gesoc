package com.testigos.gesoc.model.domain.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.testigos.gesoc.model.domain.entidades.tipoorg.TipoOrg;

@Entity
public class JuridicaIGJ extends Juridica {

    @Column
    private int codInscripcion;

    public JuridicaIGJ(Integer cuit, DireccionPostal direccPostal, String razonSocial, String nombreFicticio,
            TipoOrg tipo, int codInscripcion) {
        super(cuit, direccPostal, razonSocial, nombreFicticio, tipo);
        this.codInscripcion = codInscripcion;
    }

    public JuridicaIGJ() {
        super();
    }
}
