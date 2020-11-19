package com.testigos.gesoc.model.domain.egresos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria extends EntidadPersistente {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criterio_de_categorizacion_id")
    private @Getter @Setter CriterioDeCategorizacion criterio;

    @Column
    private @Getter @Setter String nombre;

    public Categoria(String nombre, CriterioDeCategorizacion criterio) {
        this.nombre = nombre;
        this.criterio = criterio;
    }

    boolean perteneceACriterio(CriterioDeCategorizacion crit) {
        return criterio.equals(crit);
    }
}
