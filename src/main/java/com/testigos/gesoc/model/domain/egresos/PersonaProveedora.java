package com.testigos.gesoc.model.domain.egresos;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.testigos.gesoc.model.domain.entidades.DireccionPostal;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class PersonaProveedora extends Proveedor {

    @Column
    private String apellido;

    @Column
    private Integer dni;

    public PersonaProveedora(String nombre, String apellido, Integer dni, DireccionPostal direccPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccPostal = direccPostal;
    }
}
