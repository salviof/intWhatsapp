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
        // TODO review the generated test code and remove the default call to fail.
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getGestaoToken();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }

        System.out.println(FabConfigApiWhatsapp.CODIGO_USUARIO.getCaminhoArquivoVariaveisAmbiente());;
        System.out.println(FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema());;
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();
        MensagemSimplesEnvioWhatsapp mensagemTEste = new MensagemSimplesEnvioWhatsapp();
        mensagemTEste.setCabecalho("Salvio Atendimento suporte");
        mensagemTEste.setCorpo("Uma mensagem teste muito interessante para testar a magia");
        mensagemTEste.setRodape("para sair do chamado, digite menu a qualquer momento");
        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getAcao(codigoTelefoneOrigemMensagem, "5531984178550",
                mensagemTEste).getResposta();

        ItfRespostaWebServiceSimples respostat2 = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getAcao(codigoTelefoneOrigemMensagem, "5531986831481",
                mensagemTEste).getResposta();

        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());

    }
//"wamid.HBgMNTUzMTk1MTcxNjA1FQIAERgSM0MzRkE0N0I3QkQzMzU5M
//"wamid.HBgMNTUzMTk1MTcxNjA1FQIAERgSM0MzRkE0N0I3QkQzMzU5MjZBAA==
}
