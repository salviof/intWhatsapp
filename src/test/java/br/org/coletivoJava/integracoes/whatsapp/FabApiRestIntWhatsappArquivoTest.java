/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.whatsapp;

import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

/**
 * @author salvio
 */
public class FabApiRestIntWhatsappArquivoTest extends TestesApiRest {

    public FabApiRestIntWhatsappArquivoTest() {
    }

    /**
     * Test of values method, of class FabApiRestIntWhatsappMensagem.
     */
    @Test
    public void testValues() {
        try {

            SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
            gerarCodigosChamadasEndpoint(FabApiRestIntWhatsappArquivoMidia.class);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getMessage(), t);
        }
    }

}
