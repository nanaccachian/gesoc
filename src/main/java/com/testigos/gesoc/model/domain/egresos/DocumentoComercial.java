package com.testigos.gesoc.model.domain.egresos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "documentos_comerciales")
public class DocumentoComercial extends EntidadPersistente {

    @Column
    private @Getter @Setter int num;

    @Column
    private @Getter @Setter String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presupuesto_id")
    private @Getter @Setter Presupuesto presupuesto;

    public DocumentoComercial(int num, String tipo) {
        this.num = num;
        this.tipo = tipo;
    }
}
