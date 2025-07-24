package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappArquivoMidia;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreBytes;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class IntegracaoRestIntwhatsappArquivoPdfEnvioTest {

    @Test
    public void arquivoPdfEnvioTest() throws Exception {
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getGestaoToken();
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }

        byte[] arquivo = UtilSBCoreBytes.gerarBytesPorArquivo(new File("/home/superBits/projetos/coletivoJava/source/integracao/intWhatsapp/src/main/resources/arquivos/pdfTeste.pdf"));
        String nomeArquivo = "meuPDF";
        
        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappArquivoMidia.ARQUIVO_PDF_ENVIO.getAcao(codigoTelefoneOrigemMensagem, "5531986831481", arquivo, nomeArquivo).getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());

    }

}