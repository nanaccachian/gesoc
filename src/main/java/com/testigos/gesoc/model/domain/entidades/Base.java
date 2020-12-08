package com.testigos.gesoc.model.domain.entidades;

import javax.persistence.*;

import com.testigos.gesoc.model.domain.entidades.tipoorg.TipoOrg;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Base extends Entidad {

    @Column
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "juridica_id", referencedColumnName = "id")
    private Juridica juridica;

    public Base(String desc, String nombreFicticio, TipoOrg tipo) {
        this.descripcion = desc;
        this.nombreFicticio = nombreFicticio;
        this.tipo = tipo;
    }
}
