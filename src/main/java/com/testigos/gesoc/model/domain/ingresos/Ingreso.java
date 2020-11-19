package com.testigos.gesoc.model.domain.ingresos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.egresos.Egreso;
import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "ingresos")
public class Ingreso extends EntidadPersistente {

    @Column
    private String descripcion;

    @Column
    private @Getter double valorTotal;

    @Column
    private @Getter LocalDate fechaIngreso;

    @OneToMany(mappedBy = "ingresoAsociado", cascade = CascadeType.ALL)
    private @Getter final List<Egreso> egresosAsociados = new ArrayList<>();

    public Ingreso(String descripcion, double valorTotal) {
        this.descripcion = descripcion;
        this.valorTotal = valorTotal;
    }

    public double valorDisponible() {
        return valorTotal - valorEgresosAsociados();
    }

    private double valorEgresosAsociados() {
        return egresosAsociados.stream().mapToDouble(Egreso::valorTotal).sum();
    }
}
