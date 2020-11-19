package com.testigos.gesoc.model.services.passwordValidator;

import org.junit.Assert;
import org.junit.Test;

public class ValidadorContraseniaTest {

    @Test
    public void esMalaPorArchivo_password_ContraseniaTest() {
        Assert.assertFalse(ValidadorContrasenia.validarContrasenia("password"));
    }

    @Test
    public void esMalaPorNoMAYUS_holacomoestasaishdb_ContraseniaTest() {
        Assert.assertFalse(ValidadorContrasenia.validarContrasenia("holacomoestasaishdb14"));
    }

    @Test
    public void esMalaPorNoNum_Holacomoasfasdgestas_ContraseniaTest() {
        Assert.assertFalse(ValidadorContrasenia.validarContrasenia("Holacomoasfasdgestas"));
    }

    @Test
    public void esMalaPorCaracteresRepetidos_Holacomoestas14aaaa_ContraseniaTest() {
        Assert.assertFalse(ValidadorContrasenia.validarContrasenia("Holacomoestas14aaaa"));
    }

    @Test
    public void esMalaPorCaracteresSecuenciales_Holacomoestas1abcd_ContraseniaTest() {
        Assert.assertFalse(ValidadorContrasenia.validarContrasenia("Holacomoestas1abcd"));
    }

    @Test
    public void esMalaPorCaracteresSecuencialesNum_Holacomoestas1234_ContraseniaTest() {
        Assert.assertFalse(ValidadorContrasenia.validarContrasenia("Holacomoestas1234"));
    }

    @Test
    public void esMalaPorNo8Caracteres_Holacomoestas1234_ContraseniaTest() {
        Assert.assertFalse(ValidadorContrasenia.validarContrasenia("esterA1"));
    }

    @Test
    public void esBuena_holacomoEstas1_ContraseniaTest() {
        Assert.assertTrue(ValidadorContrasenia.validarContrasenia("holacomoEstas1"));
    }
}
