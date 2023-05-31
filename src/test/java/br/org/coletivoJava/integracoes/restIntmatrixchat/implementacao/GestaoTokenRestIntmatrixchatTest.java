/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntmatrixchat.implementacao;

import br.org.coletivoJava.integracoes.matrixChat.FabApiRestIntMatrixChatUsuarios;
import com.super_bits.Super_Bits.mktMauticIntegracao.configAppp.ConfiguradorCoreMatrixChatIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class GestaoTokenRestIntmatrixchatTest {

    public GestaoTokenRestIntmatrixchatTest() {
    }
    private static GestaoTokenRestIntmatrixchat gestaoTokenSistema;

    @Test
    public void testeIntegrado() {
        SBCore.configurar(new ConfiguradorCoreMatrixChatIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        gestaoTokenSistema = (GestaoTokenRestIntmatrixchat) FabApiRestIntMatrixChatUsuarios.USUARIO_CRIAR.getGestaoToken();
        testGerarNovoToken();
    }

    /**
     * Test of validarToken method, of class GestaoTokenRestIntmatrixchat.
     */
    public void testValidarToken() {

    }

    /**
     * Test of gerarNovoToken method, of class GestaoTokenRestIntmatrixchat.
     */
    public void testGerarNovoToken() {

        gestaoTokenSistema.gerarNovoToken();

        assertTrue(gestaoTokenSistema.isTemTokemAtivo());

    }

    /**
     * Test of extrairToken method, of class GestaoTokenRestIntmatrixchat.
     */
    public void testExtrairToken() {

    }

}
