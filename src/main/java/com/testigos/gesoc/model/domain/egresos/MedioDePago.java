package com.testigos.gesoc.model.domain.egresos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "medios_de_pago")
public class MedioDePago extends EntidadPersistente {

    @Column
    private String id;

    @Column
    private String medio;

    @Column
    private String paymentID;

    public MedioDePago(String id, String medio, String paymentID) {
        this.id = id;
        this.medio = medio;
        this.paymentID = paymentID;
    }
}
