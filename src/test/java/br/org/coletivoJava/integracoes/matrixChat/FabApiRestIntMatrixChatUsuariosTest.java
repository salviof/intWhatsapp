/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.matrixChat;

import com.super_bits.Super_Bits.mktMauticIntegracao.configAppp.ConfiguradorCoreMatrixChatIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import org.junit.Test;
import testes.testesSupers.TestesApiRest;

/**
 *
 * @author salvio
 */
public class FabApiRestIntMatrixChatUsuariosTest extends TestesApiRest {

    public FabApiRestIntMatrixChatUsuariosTest() {
    }

    /**
     * Test of values method, of class FabApiRestIntMatrixChatUsuarios.
     */
    @Test
    public void testValues() {
        try {

            SBCore.configurar(new ConfiguradorCoreMatrixChatIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
            gerarCodigosChamadasEndpoint(FabApiRestIntMatrixChatUsuarios.class);

        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, t.getMessage(), t);
        }
    }

}
