package com.testigos.gesoc.model.domain.financiamiento;

import com.testigos.gesoc.model.domain.ingresos.Ingreso;
import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import com.testigos.gesoc.model.domain.usuarios.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "proyectos_de_financiamiento")
public class ProyectoDeFinanciamiento extends EntidadPersistente {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private @Getter @Setter Usuario director;

    @OneToMany(mappedBy = "proyectoAsociado", cascade = CascadeType.ALL)
    private @Getter @Setter List<Ingreso> ingresosAsociados;

    @Column
    private @Getter @Setter float montoTotal;

    @Column
    private @Getter @Setter String descripcion;

    @Column
    private @Getter @Setter float limiteDeErogacion;

    @Column
    private @Getter @Setter int cantidadPresupuestos;

    public void asociarIngreso(Ingreso ingreso) {
        if (ingresosAsociados.stream().mapToDouble(Ingreso::getMonto).sum()
                + ingreso.getMonto() < montoTotal)
            ingresosAsociados.add(ingreso);
    }

    public boolean sePuedeAgregar(Ingreso ingreso) {
        return ingresosAsociados.stream().mapToDouble(Ingreso::getMonto).sum()
                + ingreso.getMonto() <= montoTotal;
    }
}
