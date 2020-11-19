package com.testigos.gesoc.model.domain.entidades;

import org.junit.Assert;
import org.junit.Test;

import com.testigos.gesoc.model.domain.entidades.categorizador.SectoresEnum;
import com.testigos.gesoc.model.domain.entidades.tipoorg.MedianaTramo2;
import com.testigos.gesoc.model.domain.entidades.tipoorg.Micro;
import com.testigos.gesoc.model.domain.entidades.tipoorg.OSC;
import com.testigos.gesoc.model.domain.entidades.tipoorg.Pequenia;

public class CategorizadorTest {

    private final JuridicaIGJ fedesam = new JuridicaIGJ(2, null, "", "", new OSC(), 1);
    private final Base arcosDorados = new Base("", "", new Micro("", SectoresEnum.SERVICIOS, 530, 85000));
    private final Base nike = new Base("", "", new MedianaTramo2("", SectoresEnum.COMERCIO, 600, 178861000));
    private final Base UOCRA = new Base("", "", new MedianaTramo2("", SectoresEnum.COMERCIO, 1, 1));
    private final Base adidas = new Base("", "", new Pequenia("", SectoresEnum.COMERCIO, 12, 123));

    @Test(expected = RuntimeException.class)
    public void noPuedeRecategorizarEsOSC_fedesam_RecategorizarTest() {
        fedesam.recategorizar();
    }

    @Test(expected = RuntimeException.class)
    public void noEsMasPYME_nike_RecategorizarTest() {
        nike.recategorizar();
    }

    @Test
    public void noRecategoriza_adidas_RecategorizarTest() {
        adidas.recategorizar();
        Assert.assertTrue(adidas.getTipo() instanceof Pequenia);
    }

    @Test
    public void recategorizarDeMicroAMT2_mcdonalds_RecategorizarTest() {
        arcosDorados.recategorizar();
        Assert.assertTrue(arcosDorados.getTipo() instanceof MedianaTramo2);
    }

    @Test
    public void recategorizarDeMT2AMicro_UOCRA_RecategorizarTest() {
        UOCRA.recategorizar();
        Assert.assertTrue(UOCRA.getTipo() instanceof Micro);
    }
}
