package com.testigos.gesoc.model.domain.egresos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "presupuestos")
public class Presupuesto extends EntidadPersistente {

    @Column
    private @Getter @Setter String descripcion;

    @OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL)
    private @Getter List<Item> items;

    @OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL)
    private List<DocumentoComercial> documentosAsociados = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "egreso_con_presupuestos_id")
    private @Getter EgresoConPresupuestos egresoConPresupuestos;

    public Presupuesto(List<Item> items, List<DocumentoComercial> documentosAsociados,
            EgresoConPresupuestos egresoConPresupuestos) {
        this.items = items;
        this.documentosAsociados = documentosAsociados;
        this.egresoConPresupuestos = egresoConPresupuestos;
    }

    public double valorTotal() {
        return items.stream().mapToDouble(Item::valorTotal).sum();
    }

    public List<String> listaItems() {
        return items.stream().map(Item::getProducto).collect(Collectors.toList());
    }

    public void addDocumento(DocumentoComercial doc) {
        documentosAsociados.add(doc);
    }
}
