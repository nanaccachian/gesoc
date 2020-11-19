package com.testigos.gesoc.model.domain.persistentes;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;

@MappedSuperclass
public class EntidadPersistenteEgreso {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected @Getter int id;
}
