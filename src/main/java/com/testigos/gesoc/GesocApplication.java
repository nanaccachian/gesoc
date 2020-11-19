package com.testigos.gesoc;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GesocApplication {

    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(GesocApplication.class, args);
        // Calendarizacion.comenzar(18, 0);
        // Consola.ejecutar();
    }
}