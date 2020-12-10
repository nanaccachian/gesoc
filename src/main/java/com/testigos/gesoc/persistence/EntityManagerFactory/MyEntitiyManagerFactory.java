package com.testigos.gesoc.persistence.EntityManagerFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyEntitiyManagerFactory {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceUnit");
}
