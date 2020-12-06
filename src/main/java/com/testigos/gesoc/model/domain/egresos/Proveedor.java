package com.testigos.gesoc.model.domain.egresos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "proveedores")
public abstract class Proveedor extends EntidadPersistente {

    @Column
    protected @Getter String nombre;

    @Column
    protected @Getter String direccPostal;

    public String nombreClase() {
        return this.getClass().getSimpleName();
    }
}
