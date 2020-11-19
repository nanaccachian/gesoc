package com.testigos.gesoc.model.domain.entidades.tipoorg;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.testigos.gesoc.model.domain.entidades.categorizador.Categorizador;
import com.testigos.gesoc.model.domain.entidades.categorizador.SectoresEnum;
import lombok.Getter;

@MappedSuperclass
public abstract class Empresa extends TipoOrg {

    @Column
    protected @Getter String actividad;

    @Column
    protected @Getter SectoresEnum sector;

    @Column
    protected @Getter Integer cantPersonal;

    @Column
    protected @Getter double promVentasAnuales;

    @Override
    public TipoOrg recategorizar() {
        return Categorizador.recategorizar(this);
    }

    public void setSector(SectoresEnum sector) {
        this.sector = sector;
        recategorizar();
    }

    public void setCantPersonal(Integer cantPersonal) {
        this.cantPersonal = cantPersonal;
        recategorizar();
    }

    public void setPromVentasAnuales(double promVentasAnuales) {
        this.promVentasAnuales = promVentasAnuales;
        recategorizar();
    }
}