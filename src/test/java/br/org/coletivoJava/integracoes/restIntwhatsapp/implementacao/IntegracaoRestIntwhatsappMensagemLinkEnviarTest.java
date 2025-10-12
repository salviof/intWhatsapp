/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
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
public class IntegracaoRestIntwhatsappMensagemLinkEnviarTest {

    public IntegracaoRestIntwhatsappMensagemLinkEnviarTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of gerarCorpoRequisicao method, of class
     * IntegracaoRestIntwhatsappMensagemLinkEnviar.
     */
    @Test
    public void testGerarCorpoRequisicao() {
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();
        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_LINK_ENVIAR
                .getAcao(codigoTelefoneOrigemMensagem, "5531984178550", "Apenas testes", "abrir area do cliente", "https://atendimento.casanovadigital.com.br").getResposta();
        System.out.println(resposta.getRespostaTexto());
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());
    }

}
