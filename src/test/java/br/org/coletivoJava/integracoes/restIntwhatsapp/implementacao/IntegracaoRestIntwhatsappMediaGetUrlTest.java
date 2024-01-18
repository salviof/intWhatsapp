/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMedia;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreBytes;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreOutputs;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.FabTipoArquivoConhecido;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntwhatsappMediaGetUrlTest {

    public IntegracaoRestIntwhatsappMediaGetUrlTest() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMedia.MEDIA_GET_URL.getGestaoToken();
        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }
        ItfAcaoApiRest acaoGetUrl = FabApiRestIntWhatsappMedia.MEDIA_GET_URL.getAcao("614543020758485");
        ItfRespostaWebServiceSimples resposta = acaoGetUrl.getResposta();
        System.out.println(resposta.getRespostaTexto());
        String url = resposta.getRespostaComoObjetoJson().getString("url");
        String tipoArquivo = resposta.getRespostaComoObjetoJson().getString("mime_type");
        FabTipoArquivoConhecido.getTipoArquivoByNomeArquivo(tipoArquivo);
        Map<String, String> header = new IntegracaoRestIntwhatsapp_HeaderPadrao(acaoGetUrl).getHeaderPadrao();
        InputStream imagem = UTilSBCoreInputs.getStreamBuffredByURL(url, -1, -1, header);
        UtilSBCoreOutputs.salvarArquivoByte(UtilSBCoreBytes.gerarBytePorInputstream(imagem), "/home/salvio/Imagens/teste.jpg");
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());

    }

}
