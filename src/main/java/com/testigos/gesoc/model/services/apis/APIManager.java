package com.testigos.gesoc.model.services.apis;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.testigos.gesoc.model.services.apis.domain.City;
import com.testigos.gesoc.model.services.apis.domain.Country;
import com.testigos.gesoc.model.services.apis.domain.Currency;
import com.testigos.gesoc.model.services.apis.domain.State;
import org.json.JSONArray;

public class APIManager {

    public static String request(String slug) {
        String uri = "https://api.mercadolibre.com/" + slug;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(uri)).build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join().body();
    }

    public static List<Currency> getCurrencies() {
        String response = request("currencies/");
        JSONArray _currencies = new JSONArray(response);

        List<Currency> currencies = new ArrayList<>();
        for (int i = 0; i < _currencies.length(); i++) {
            String id = _currencies.getJSONObject(i).getString("id");
            String symbol = _currencies.getJSONObject(i).getString("symbol");
            String description = _currencies.getJSONObject(i).getString("description");
            int decimal_places = _currencies.getJSONObject(i).getInt("decimal_places");
            currencies.add(new Currency(id, description, symbol, decimal_places));
        }

        return currencies;
    }

    public static City getCity(String id) {
        String response = request("classified_locations/cities/" + id);
        return City.parse(response);
    }

    public static State getState(String id) {
        String response = request("classified_locations/states/" + id);
        return State.parse(response);
    }

    public static Country getCountry(String id) {
        String response = request("classified_locations/countries/" + id);
        return Country.parse(response);
    }

    public static List<String> getCountries(List<String> countries_names) {
        String response = request("classified_locations/countries");
        JSONArray countries = new JSONArray(response);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < countries.length(); i++) {
            ids.add(countries.getJSONObject(i).getString("id"));
            countries_names.add(countries.getJSONObject(i).getString("name"));
        }

        return ids;
    }
}