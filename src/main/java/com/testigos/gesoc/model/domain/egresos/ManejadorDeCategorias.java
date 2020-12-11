package com.testigos.gesoc.model.domain.egresos;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "manejadores_de_categorias")
public class ManejadorDeCategorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter String id;

    @Column
    private @Getter String descripcion;

    @OneToMany(mappedBy = "manejadorDeCategorias", cascade = CascadeType.ALL)
    private @Getter List<CriterioDeCategorizacion> categorizacionesAplicables;

    public ManejadorDeCategorias(String nombre, List<CriterioDeCategorizacion> categorizacionesAplicables) {
        this.id = nombre;
        this.categorizacionesAplicables = categorizacionesAplicables;
    }

    public CriterioDeCategorizacion encontrarCriterio(String critIngresado) {
        for (CriterioDeCategorizacion crit : categorizacionesAplicables) {
            if (crit.getNombre().equals(critIngresado))
                return crit;
        }
        return null;
    }

    public void agregarCategorizaciones(List<CriterioDeCategorizacion> lista) {
        categorizacionesAplicables.addAll(lista);
    }

    public Categoria buscarCategoria(String nombre) {
        for (CriterioDeCategorizacion crit : categorizacionesAplicables) {
            for (Categoria cat : crit.getCategorias()) {
                if (nombre.equals(cat.getNombre()))
                    return cat;
            }
        }
        return null;
    }
}
