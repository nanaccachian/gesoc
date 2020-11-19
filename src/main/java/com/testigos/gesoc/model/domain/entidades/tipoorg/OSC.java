package com.testigos.gesoc.model.domain.entidades.tipoorg;

import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class OSC extends TipoOrg {

    @Override
    public TipoOrg recategorizar() {
        throw new RuntimeException("No te podes recategorizar, sos OSC");
    }
}
