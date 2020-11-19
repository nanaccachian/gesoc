package com.testigos.gesoc.model.domain.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.testigos.gesoc.model.domain.entidades.tipoorg.TipoOrg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Juridica extends Entidad {

    @OneToMany(mappedBy = "juridica", cascade = CascadeType.ALL)
    protected @Setter List<Base> bases = new ArrayList<>();

    @Column
    protected @Getter Integer cuit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_postal_id")
    protected DireccionPostal direccPostal;

    @Column
    protected String razonSocial;

    public Juridica(Integer cuit, DireccionPostal direccPostal, String razonSocial, String nombreFicticio,
            TipoOrg tipo) {
        this.cuit = cuit;
        this.direccPostal = direccPostal;
        this.razonSocial = razonSocial;
        this.nombreFicticio = nombreFicticio;
        this.tipo = tipo;
    }

    public boolean soyYo(Entidad entidad) {
        return this.cuit.equals(entidad.getCuit());
    }

    public void agregarBase(Base base) {
        bases.add(base);
    }
}
