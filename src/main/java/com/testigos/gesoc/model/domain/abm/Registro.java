package com.testigos.gesoc.model.domain.abm;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Document(collection = "Registro")
public class Registro {

    @Id
    private @Setter @Getter String id;
    private @Setter @Getter TipoRegistro tipoRegistro;
    private @Setter @Getter Object entidad; // Tiene object para que pueda ser cualquier cosa, después lo vemos

    private @Setter @Getter String descripcion;

    public Registro(TipoRegistro tipoRegistro, Object entidad, String descripcion) {
        this.tipoRegistro = tipoRegistro;
        this.entidad = entidad;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Registro{" + "id=" + id + ", descripcion='" + descripcion + '\'' + '}';
    }
}
