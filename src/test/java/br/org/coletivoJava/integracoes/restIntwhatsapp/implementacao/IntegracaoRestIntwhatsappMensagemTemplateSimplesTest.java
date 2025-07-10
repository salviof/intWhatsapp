/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.ParametroMensgemWhatsapp;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.google.common.collect.Lists;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author salvio
 */
public class IntegracaoRestIntwhatsappMensagemTemplateSimplesTest {


    public IntegracaoRestIntwhatsappMensagemTemplateSimplesTest() {
    }

    /**
     * Test of gerarCorpoRequisicao method, of class
     * IntegracaoRestIntwhatsappMensagemTemplateSimples.
     */
    @Test
    public void testeParametroTexto() {
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES.getGestaoToken();
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();
        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }
        List<ParametroMensgemWhatsapp> lista = Lists.newArrayList(new ParametroMensgemWhatsapp(1, "Sálvio furbino", false));
//        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES
//                .getAcao(codigoTelefoneOrigemMensagem, "5531984178550",
//                        "pedido_contato_cliente", lista).getResposta();
        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES
                .getAcao(codigoTelefoneOrigemMensagem, "5531986831481",
                        "pedido_contato_cliente", lista).getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());
    }

    @Test
    public void testeParametroDocumentTexto() {
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES.getGestaoToken();
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }
        List<ParametroMensgemWhatsapp> lista = Lists.newArrayList(new ParametroMensgemWhatsapp(1, "https://casanovadigital.com.br/wp-content/uploads/2023/10/SOBRE-A-CASA-INSTITUCIONAL_compressed.pdf",
                "NossaCasa.pdf", "document", true), new ParametroMensgemWhatsapp(1, "Sálvio furbino", false));
//        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES
//                .getAcao(codigoTelefoneOrigemMensagem, "5531984178550",
//                        "boas_vindas_camila", lista).getResposta();
        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES
                .getAcao(codigoTelefoneOrigemMensagem, "5531986831481",
                        "boas_vindas_camila", lista).getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());
    }

    @Test
    public void testeMultiplosbotoes() {
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES.getGestaoToken();
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }
        //button
        List<ParametroMensgemWhatsapp> lista = Lists.newArrayList(
                new ParametroMensgemWhatsapp(0, "Sálvio furbino", true),
                new ParametroMensgemWhatsapp(0, "Pedro da silva", false),
                new ParametroMensgemWhatsapp(1, "Briefing do goolge ads", false),
                new ParametroMensgemWhatsapp(0, "12343_24234_123123123123./html", "Token Agenda chat", "button", false),
                new ParametroMensgemWhatsapp(1, "12343_24234_123123123333./html", "Token Agenda representante", "button", false)
        );
        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES
                .getAcao(codigoTelefoneOrigemMensagem, "5531986831481",
                        "resumoprojetogenerico", lista).getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());
    }

}
