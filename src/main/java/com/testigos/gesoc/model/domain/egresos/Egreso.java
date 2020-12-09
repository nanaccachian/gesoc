package com.testigos.gesoc.model.domain.egresos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistenteEgreso;
import com.testigos.gesoc.model.domain.entidades.Entidad;
import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "egresos")
public class Egreso extends EntidadPersistenteEgreso {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comprador_id")
    protected @Getter @Setter Entidad comprador;

    @Column
    protected @Getter @Setter LocalDate fechaOperacion;

    @Column
    protected @Getter @Setter String descripcion;

    @Column
    protected @Getter @Setter String medioPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingreso_asociado_id")
    protected @Getter @Setter Ingreso ingresoAsociado;

    @OneToMany(mappedBy = "egreso", cascade = CascadeType.ALL)
    protected @Getter @Setter List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    protected @Getter @Setter Proveedor vendedor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento_comercial_id")
    protected @Getter @Setter DocumentoComercial documento = null;

    public Egreso(Entidad comprador, LocalDate fechaOperacion, String medioPago, List<Item> items, Proveedor vendedor) {
        this.comprador = comprador;
        this.fechaOperacion = fechaOperacion;
        this.medioPago = medioPago;
        this.items = items;
        this.vendedor = vendedor;
    }

    public List<String> listaItems() {
        return items.stream().map(Item::getProducto).collect(Collectors.toList());
    }

    public double valorTotal() {
        if (items == null || items.isEmpty())
            return 0;
        else
            return items.stream().mapToDouble(Item::valorTotal).sum();
    }

}
