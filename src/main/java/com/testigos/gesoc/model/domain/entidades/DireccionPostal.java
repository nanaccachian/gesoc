package com.testigos.gesoc.model.domain.entidades;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import com.testigos.gesoc.model.services.apis.domain.City;
import com.testigos.gesoc.model.services.apis.domain.Country;
import com.testigos.gesoc.model.services.apis.domain.State;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "direcciones_postales")
public class DireccionPostal extends EntidadPersistente {

    @Column @Nullable
    private String calle;

    @Column @Nullable
    private int altura;

    @Column @Nullable
    private int piso = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ciudad_id")
    private City ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia_id")
    private State provincia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pais_id")
    private Country pais;

    public DireccionPostal(String calle, int altura, int piso, City ciudad, State provincia, Country pais) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
    }
}
