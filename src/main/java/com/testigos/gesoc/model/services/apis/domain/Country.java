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
@Table(name = "country")
public class Country {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String locale;

    @Column
    private String currency_id;

    @Column
    private String decimal_separator;

    @Column
    private String thousands_separator;

    @Column
    private String time_zone;

    @Transient
    private List<State> states;

    @Data
    @AllArgsConstructor
    public static class State {
        private String id;
        private String name;
    }

    public static Country parse(String jsonString) {

        JSONObject country = new JSONObject(jsonString);

        String id = country.getString("id");
        String name = country.getString("name");
        String locale = country.getString("locale");
        String currency_id = country.getString("currency_id");
        String decimal_separator = country.getString("decimal_separator");
        String thousands_separator = country.getString("thousands_separator");
        String time_zone = country.getString("time_zone");

        JSONArray states = country.getJSONArray("states");

        List<State> _states = new ArrayList<>();
        for (int i = 0; i < states.length(); i++)
            _states.add(new State(states.getJSONObject(i).getString("id"), states.getJSONObject(i).getString("name")));

        return new Country(id, name, locale, currency_id, decimal_separator, thousands_separator, time_zone, _states);
    }
}
