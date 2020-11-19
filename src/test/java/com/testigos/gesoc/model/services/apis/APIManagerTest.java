package com.testigos.gesoc.model.services.apis;

import com.testigos.gesoc.model.services.apis.domain.City;
import com.testigos.gesoc.model.services.apis.domain.Currency;
import com.testigos.gesoc.model.services.apis.domain.State;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class APIManagerTest {

    @Test
    public void testMonedas() {

        List<Currency> monedas = APIManager.getCurrencies();

        System.out.println(monedas.get(0).id);

        Assert.assertTrue(monedas.stream().anyMatch(m -> m.id.equalsIgnoreCase("ARS")));
    }

    @Test
    public void testPaises() {

        List<String> countries_names = new ArrayList<>();
        List<String> countries_ids = APIManager.getCountries(countries_names);

        System.out.println(countries_ids.get(0));

        Assert.assertTrue(countries_ids.stream().anyMatch(m -> m.equalsIgnoreCase("AR")));
    }

    @Test
    public void testProvincia() {

        State state = APIManager.getState("UY-RO");

        System.out.println(state.getId());

        Assert.assertEquals("UY-RO", state.getId());
    }

    @Test
    public void testCity() {

        City city = APIManager.getCity("VVktUk8xOCBEZSBKdWxpbw");

        System.out.println(city.getId());

        Assert.assertEquals("VVktUk8xOCBEZSBKdWxpbw", city.getId());
    }
}
