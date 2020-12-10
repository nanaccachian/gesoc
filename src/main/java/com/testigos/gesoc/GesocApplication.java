package com.testigos.gesoc;

import java.util.Collections;

import javax.annotation.PostConstruct;

import com.testigos.gesoc.model.services.budgetValidator.Calendarizacion;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GesocApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication gesoc = new SpringApplication(GesocApplication.class);
        gesoc.setDefaultProperties(Collections.singletonMap("server.port", "5000"));
        gesoc.run(args);
    }

    @PostConstruct
    public void initialize() throws SchedulerException {
        Calendarizacion.comenzar(18, 0);
    }
}