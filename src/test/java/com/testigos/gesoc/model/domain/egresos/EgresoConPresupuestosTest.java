package com.testigos.gesoc.model.domain.egresos;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EgresoConPresupuestosTest {

    MenorPresupuesto mp = MenorPresupuesto.getInstance();

    Item lapicera1 = new Item("lapicera1", 20, 2);
    Item lapiz1 = new Item("lapiz1", 15, 2);
    Item regla1 = new Item("regla1", 10, 2);// 90

    Item lapicera2 = new Item("lapicera2", 25, 2);
    Item lapiz2 = new Item("lapiz2", 20, 2);
    Item regla2 = new Item("regla2", 10, 2);// 110

    Item lapicera3 = new Item("lapicera3", 30, 2);
    Item lapiz3 = new Item("lapiz3", 10, 2);
    Item regla3 = new Item("regla3", 15, 2);// 110

    List<Item> productos1 = Arrays.asList(lapicera1, lapiz1, regla1);
    List<Item> productos2 = Arrays.asList(lapicera2, lapiz2, regla2);
    List<Item> productos3 = Arrays.asList(lapicera3, lapiz3, regla3);

    EgresoConPresupuestos egresoConPresupuestos = new EgresoConPresupuestos(null, null,
            new MedioDePago("id", "medio", "paymentID"), null, new PersonaProveedora("nombre", "apellido", 1, null),
            mp);

    Presupuesto presupuestoUtiles1 = new Presupuesto(productos1, null, egresoConPresupuestos);
    Presupuesto presupuestoUtiles2 = new Presupuesto(productos2, null, egresoConPresupuestos);
    Presupuesto presupuestoUtiles3 = new Presupuesto(productos3, null, egresoConPresupuestos);

    List<Presupuesto> todosLosPresupuestos = Arrays.asList(presupuestoUtiles1, presupuestoUtiles2, presupuestoUtiles3);

    @Before
    public void inicioSesion() {
        egresoConPresupuestos.setPresupuestoElegido(presupuestoUtiles1);
        egresoConPresupuestos.setTodosLosPresupuestos(todosLosPresupuestos);
    }

    @Test
    public void esValidaLaCompra() {
        Assert.assertTrue(egresoConPresupuestos.esValido());
    }
}
