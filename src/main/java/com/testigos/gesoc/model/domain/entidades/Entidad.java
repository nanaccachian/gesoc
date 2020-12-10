package com.testigos.gesoc.model.domain.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.egresos.ManejadorDeCategorias;
import com.testigos.gesoc.model.domain.entidades.tipoorg.TipoOrg;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "entidades")
public abstract class Entidad extends EntidadPersistente {

    @OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL)
    protected List<Egreso> egresos = new ArrayList<>();

    @OneToMany(mappedBy = "entidad", cascade = CascadeType.ALL)
    protected @Getter List<Ingreso> ingresos = new ArrayList<>();

    @Column
    protected @Getter @Setter String nombreFicticio;

    @Column
    protected @Getter @Setter int cantidadPresupuestosRequeridos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_de_organizacion_id")
    protected @Getter TipoOrg tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manejadores_de_categorias_id")
    protected @Getter ManejadorDeCategorias manejadorDeCategorias;

    public void recategorizar() {
        tipo = tipo.recategorizar();
    }

    public void agregarEgreso(Egreso egreso) {
        egresos.add(egreso);
    }

    protected Integer getCuit() {
        return null;
    }

    public String nombreClase() {
        return this.getClass().getSimpleName();
    }
}
