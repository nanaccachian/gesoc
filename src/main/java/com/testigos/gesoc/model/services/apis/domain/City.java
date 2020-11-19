package com.testigos.gesoc.model.services.apis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cities")
public class City {

    @Id
    String id;

    @Column
    String name;

    @Column(name = "state_id")
    String state;

    @Column(name = "country_id")
    String country;

    public static City parse(String jsonString) {

        JSONObject city = new JSONObject(jsonString);

        String id = city.getString("id");
        String name = city.getString("name");
        String state = city.getJSONObject("state").getString("id");
        String country = city.getJSONObject("country").getString("id");

        return new City(id, name, state, country);
    }
}
