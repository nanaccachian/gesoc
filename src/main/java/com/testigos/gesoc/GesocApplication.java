package com.testigos.gesoc;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GesocApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication gesoc = new SpringApplication(GesocApplication.class);
        gesoc.setDefaultProperties(Collections.singletonMap("server.port", "5000"));
        gesoc.run(args);
    }
}