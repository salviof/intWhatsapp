/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCBytes;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import org.junit.Test;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author salvio
 */
public class IntegracaoRestIntwhatsappMensagemAudioEnviarTest {

    public IntegracaoRestIntwhatsappMensagemAudioEnviarTest() {
    }

    @Test
    public void testGerarUrlRequisicao() {

        // TODO review the generated test code and remove the default call to fail.
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getGestaoToken();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }
        try {
            testeEnvioMedia();
        } catch (Exception ex) {
            Logger.getLogger(IntegracaoRestIntwhatsappMensagemAudioEnviarTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();
        ItfRespostaWebServiceSimples respMedia = FabApiRestIntWhatsappMensagem.MENSAGEM_AUDIO_ENVIAR.getAcao(codigoTelefoneOrigemMensagem, "+5531986831481", "906078971628338").getResposta();
        System.out.println(respMedia.getRespostaTexto());
        //906078971628338

        //ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getAcao("5531984178550",
        //        "Mais um teste Apenas teste :P").getResposta();
        //assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());
    }

    public static void testeEnvioMedia() throws Exception {
        byte[] arquivo = UtilCRCBytes.gerarBytesPorArquivo(new File("/home/superBits/projetos/coletivoJava/source/integracao/intWhatsapp/src/main/resources/arquivos/audioExemplo.ogg"));
        String codigo = UtilSBApiWhatsapp.mediaUpload(arquivo, "audioTeste", "audio/ogg"); // Exibe a resposta
        //605742645208253
        System.out.println(codigo);
    }

}
