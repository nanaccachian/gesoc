package com.testigos.gesoc.model.domain.egresos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.testigos.gesoc.model.domain.usuarios.Usuario;
import com.testigos.gesoc.model.services.budgetValidator.ValidadorPresupuestos;
import com.testigos.gesoc.model.domain.entidades.Entidad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "egresos_con_presupuestos")
public class EgresoConPresupuestos extends Egreso {

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "criterio_id")
    private @Getter @Setter CriterioSeleccion criterio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "presupuesto_elegido_id")
    private @Getter @Setter Presupuesto presupuestoElegido;

    @OneToMany(mappedBy = "egresoConPresupuestos", cascade = CascadeType.ALL)
    private @Getter @Setter List<Presupuesto> todosLosPresupuestos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revisor")
    private @Getter @Setter Usuario revisor;

    @Column
    private @Getter @Setter boolean esValidoElPresupuesto = false;

    public EgresoConPresupuestos(Entidad comprador, LocalDate fechaOperacion, String medioPago, List<Item> items,
            Proveedor vendedor, CriterioSeleccion criterio) {
        super(comprador, fechaOperacion, medioPago, items, vendedor);
        this.criterio = criterio;
    }

    public boolean esValido() {
        return ValidadorPresupuestos.validar(this);
    }

    public void addPresupuesto(Presupuesto presupuesto) {
        todosLosPresupuestos.add(presupuesto);
    }
}