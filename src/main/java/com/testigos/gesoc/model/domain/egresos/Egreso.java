package com.testigos.gesoc.model.domain.egresos;

import java.time.LocalDate;
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
    protected @Setter Entidad comprador;

    @Column
    protected @Getter LocalDate fechaOperacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medio_de_pago_id")
    protected MedioDePago medioPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingreso_asociado_id")
    protected @Getter @Setter Ingreso ingresoAsociado;

    @OneToMany(mappedBy = "egreso", cascade = CascadeType.ALL)
    protected @Getter List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    protected @Getter Proveedor vendedor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "documento_comercial_id")
    protected @Setter DocumentoComercial documento = null;

    public Egreso(Entidad comprador, LocalDate fechaOperacion, MedioDePago medioPago, List<Item> items,
            Proveedor vendedor) {
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
        return items.stream().mapToDouble(Item::valorTotal).sum();
    }

}
