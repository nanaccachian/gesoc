package com.testigos.gesoc;

import com.testigos.gesoc.model.services.budgetValidator.Calendarizacion;
import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class GesocApplication {

    public static void main(String[] args) {
        SpringApplication.run(GesocApplication.class, args);
    }

    @PostConstruct
    public void initialize() throws SchedulerException {
        Calendarizacion.comenzar(18, 0);
    }
}