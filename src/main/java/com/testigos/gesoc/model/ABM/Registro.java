package com.testigos.gesoc.model.ABM;

import com.testigos.gesoc.model.domain.entidades.Entidad;
import com.testigos.gesoc.model.domain.persistentes.EntidadPersistente;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Registro")
public class Registro {

    @Id
    String id;
    TipoRegistro tipoRegistro;
    Object entidad; //Tiene object para que pueda ser cualquier cosa, después lo vemos
    String descripcion;

    public Registro(TipoRegistro tipoRegistro, Object entidad, String descripcion) {
        this.tipoRegistro = tipoRegistro;
        this.entidad = entidad;
        this.descripcion = descripcion;
    }

    public Registro() {
    }

    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Object getEntidad() {
        return entidad;
    }

    public void setEntidad(Object entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
