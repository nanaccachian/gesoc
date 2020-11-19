package com.testigos.gesoc.model.domain.egresos;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "criterios_de_categorizacion")
public class CriterioDeCategorizacion extends EntidadPersistente {

    @Column
    private @Getter @Setter String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criterio_de_categorizacion_id")
    private @Getter @Setter CriterioDeCategorizacion catPadre = null;

    @OneToMany(mappedBy = "catPadre", cascade = CascadeType.ALL)
    private @Getter final List<CriterioDeCategorizacion> catHijas = new ArrayList<>();

    @OneToMany(mappedBy = "criterio", cascade = CascadeType.ALL)
    private @Getter @Setter List<Categoria> categorias = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manejadorDeCategorias_id")
    protected ManejadorDeCategorias manejadorDeCategorias;

    public CriterioDeCategorizacion(String nombre) {
        this.nombre = nombre;
    }

    public void addCategoria(Categoria cat) {
        categorias.add(cat);
    }

    public void addCriterioHijo(CriterioDeCategorizacion crit) {
        catHijas.add(crit);
    }
}
