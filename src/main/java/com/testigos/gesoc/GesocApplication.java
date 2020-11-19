package com.testigos.gesoc;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GesocApplication {

    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(GesocApplication.class, args);
        // Calendarizacion.comenzar(18, 0);
        // System.out.println("Bienvenido a GeSOC Software Testigos Inc.");
        // System.out.println("El lugar donde podras realizar todas las operaciones que
        // nosotros queremos que realices");
        // Consola.ejecutar();
        // System.out.println("Desde GeSOC te decimos adios, vuelva pronto");
    }
}