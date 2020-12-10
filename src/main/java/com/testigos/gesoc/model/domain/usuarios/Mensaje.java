package com.testigos.gesoc.model.domain.usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "mensajes")
public class Mensaje extends EntidadPersistente {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario user;

    @Column
    private @Getter String fecha;

    @Column
    private @Getter String mensaje;

    public Mensaje(Usuario user, String mensaje, String fecha) {
        this.user = user;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }
}
