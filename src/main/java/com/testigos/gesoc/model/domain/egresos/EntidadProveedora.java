package com.testigos.gesoc.model.domain.egresos;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
public class EntidadProveedora extends Proveedor {

    @Column
    private @Getter @Setter Integer cuit;

    @Column
    private @Getter @Setter String razonSocial;

    public EntidadProveedora(Integer cuit, String razonSocial, String direccPostal) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.direccPostal = direccPostal;
        this.nombre = razonSocial;
    }
}
