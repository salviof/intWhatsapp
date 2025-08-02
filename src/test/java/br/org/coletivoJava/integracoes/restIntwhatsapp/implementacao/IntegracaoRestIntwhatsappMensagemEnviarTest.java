/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.mensagem.MensagemSimplesEnvioWhatsapp;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author salvio
 */
public class IntegracaoRestIntwhatsappMensagemEnviarTest {

    public IntegracaoRestIntwhatsappMensagemEnviarTest() {
    }

    @Test
    public void testSomeMethod() {

        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getGestaoToken();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }

        System.out.println(FabConfigApiWhatsapp.CODIGO_USUARIO.getCaminhoArquivoVariaveisAmbiente());
        System.out.println(FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema());

        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();
        MensagemSimplesEnvioWhatsapp mensagemTeste = new MensagemSimplesEnvioWhatsapp();
        mensagemTeste.setCabecalho("Salvio Atendimento suporte");
        mensagemTeste.setCorpo("Uma mensagem teste muito interessante para testar a magia");
        mensagemTeste.setRodape("para sair do chamado, digite menu a qualquer momento");

        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getAcao(codigoTelefoneOrigemMensagem, "5531984178550",
                mensagemTeste).getResposta();

        //    ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getAcao(codigoTelefoneOrigemMensagem, "5531986831481",
        //            mensagemTeste).getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());

    }
//"wamid.HBgMNTUzMTk1MTcxNjA1FQIAERgSM0MzRkE0N0I3QkQzMzU5M
//"wamid.HBgMNTUzMTk1MTcxNjA1FQIAERgSM0MzRkE0N0I3QkQzMzU5MjZBAA==
}
