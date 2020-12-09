package com.testigos.gesoc.model.domain.entidades.tipoorg;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "tipos_de_organizacion")
public abstract class TipoOrg extends EntidadPersistente {

    public TipoOrg recategorizar() {
        return null;
    }

    public String nombreClase() {
        return this.getClass().getSimpleName();
    }
}
