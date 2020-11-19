package com.testigos.gesoc.model.domain.egresos;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.testigos.gesoc.model.domain.entidades.DireccionPostal;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class EntidadProveedora extends Proveedor {

    @Column
    private Integer cuit;

    @Column
    private String razonSocial;

    public EntidadProveedora(Integer cuit, String razonSocial, DireccionPostal direccPostal) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.direccPostal = direccPostal;
    }
}
