package com.testigos.gesoc.model.domain.egresos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item extends EntidadPersistente {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "egreso_id")
    private @Setter Egreso egreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presupuesto_id")
    private @Setter @Getter Presupuesto presupuesto;

    @Column
    private @Getter @Setter String producto;

    @Column
    private @Getter @Setter double valorUnitario;

    @Column
    private @Getter @Setter int cantidad;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Categoria> categorizacion = new ArrayList<>();

    public Item(String producto, double valorUnitario, int cantidad) {
        this.producto = producto;
        this.valorUnitario = valorUnitario;
        this.cantidad = cantidad;
    }

    public double valorTotal() {
        return cantidad * valorUnitario;
    }

    public void categorizar(Categoria cat) {
        if (noTieneCriterio(cat))
            categorizacion.add(cat);
        else
            System.out.println("Ya estas categorizado segun ese criterio");
    }

    private boolean noTieneCriterio(Categoria cat) {
        return categorizacion.stream().anyMatch(cate -> cat.perteneceACriterio(cate.getCriterio()));
    }
}
