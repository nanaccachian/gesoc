package com.testigos.gesoc.model.services.apis.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    public String id;

    @Column
    public String description;

    @Column
    public String symbol;

    @Column
    public int decimal_places;
}
