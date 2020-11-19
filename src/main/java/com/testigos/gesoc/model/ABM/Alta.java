package com.testigos.gesoc.model.ABM;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Alta")
public class Alta {

    @Id
    String id;
    String descripcion;

    public Alta(String descripcion) {
        this.descripcion = descripcion;
    }

    public Alta() {
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

    @Override
    public String toString() {
        return "Alta{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
