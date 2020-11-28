package com.testigos.gesoc.model.domain.egresos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "criterio_id")
    private @Getter CriterioSeleccion criterio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "presupuesto_elegido_id")
    private @Getter @Setter Presupuesto presupuestoElegido;

    @OneToMany(mappedBy = "egresoConPresupuestos", cascade = CascadeType.ALL)
    private @Getter @Setter List<Presupuesto> todosLosPresupuestos = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter final List<Usuario> revisores = new ArrayList<>();

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

    public void addRevisor(Usuario usuario) {
        revisores.add(usuario);
    }

    public boolean esRevisor(Usuario usuario) {
        return revisores.contains(usuario);
    }
}