package com.testigos.gesoc.model.domain.egresos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "manejadores_de_categorias")
public class ManejadorDeCategorias {

    @Id
    private @Getter String id;

    @OneToMany(mappedBy = "manejadorDeCategorias", cascade = CascadeType.ALL)
    private List<CriterioDeCategorizacion> categorizacionesAplicables;

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
