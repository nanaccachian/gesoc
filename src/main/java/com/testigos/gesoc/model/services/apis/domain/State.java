package com.testigos.gesoc.model.services.apis.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.json.JSONArray;
import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "states")
public class State {

    @Id
    private String id;

    @Column
    private String name;

    @Column(name = "country_id")
    private String country;

    @Transient
    private List<City> cities;

    @AllArgsConstructor
    @Data
    public static class City {
        private String id;
        private String name;
    }

    public static State parse(String jsonString) {

        JSONObject state = new JSONObject(jsonString);

        String id = state.getString("id");
        String name = state.getString("name");
        String country = state.getJSONObject("country").getString("id");

        JSONArray cities = state.getJSONArray("cities");

        List<City> _cities = new ArrayList<>();
        for (int i = 0; i < cities.length(); i++)
            _cities.add(new City(cities.getJSONObject(i).getString("id"), cities.getJSONObject(i).getString("name")));

        return new State(id, name, country, _cities);
    }
}
