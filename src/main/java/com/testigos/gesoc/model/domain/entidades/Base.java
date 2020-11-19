package com.testigos.gesoc.model.domain.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.testigos.gesoc.model.domain.entidades.tipoorg.TipoOrg;

@Entity
public class Base extends Entidad {

    @Column
    private final String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "juridica_id", referencedColumnName = "id")
    private Juridica juridica;

    public Base(String desc, String nombreFicticio, TipoOrg tipo) {
        this.descripcion = desc;
        this.nombreFicticio = nombreFicticio;
        this.tipo = tipo;
    }
}
