package com.testigos.gesoc.model.domain.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.testigos.gesoc.model.domain.egresos.EgresoConPresupuestos;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter List<EgresoConPresupuestos> egresosConPresupuestos = new ArrayList<>();

    public Usuario(String username, String password, String permisos) {
        this.username = username;
        this.password = password;
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "Usuario [password=" + password + ", permisos=" + permisos + ", username=" + username + "]";
    }
}
