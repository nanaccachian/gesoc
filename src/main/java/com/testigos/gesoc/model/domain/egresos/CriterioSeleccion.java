package com.testigos.gesoc.model.domain.egresos;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "criterios_seleccion")
public abstract class CriterioSeleccion extends EntidadPersistente {

    public Optional<Presupuesto> presupuestoElegido(List<Presupuesto> presupuestos) {
        return Optional.empty();
    }

    public String nombreClase() {
        return this.getClass().getSimpleName();
    }
}
