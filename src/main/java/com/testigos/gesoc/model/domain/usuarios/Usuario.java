package com.testigos.gesoc.model.domain.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
import com.testigos.gesoc.model.domain.entidades.Entidad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    private @Getter @Setter String username;

    @Column
    private @Getter @Setter String password;

    @Column
    private @Getter @Setter String permisos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidad_id")
    private @Getter @Setter Entidad entidad;

    @Column
    private @Getter @Setter String name;

    @Column
    private @Getter @Setter String surname;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter List<EgresoConPresupuestos> egresosConPresupuestos = new ArrayList<>();

    public Usuario(String username, String password, String permisos, String name, String surname, Entidad entidad) {
        this.username = username;
        this.password = password;
        this.permisos = permisos;
        this.name = name;
        this.surname = surname;
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "Usuario [entidad=" + entidad + ", name=" + name + ", password=" + password + ", permisos=" + permisos
                + ", surname=" + surname + ", username=" + username + "]";
    }
}
