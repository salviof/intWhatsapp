package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappArquivoMidia;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IntegracaoRestIntwhatsappArquivoImagemEnvioTest {

    @Test
    public void arquivoImagemEnvioTest() {
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getGestaoToken();
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }
        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappArquivoMidia.ARQUIVO_IMAGEM_ENVIO.getAcao(codigoTelefoneOrigemMensagem, "5531986831481", "https://thumbs.dreamstime.com/b/padr%C3%A3o-de-grade-transparente-png-do-photoshop-elemento-vetor-fundo-quadro-verifica%C3%A7%C3%A3o-cinza-e-branco-175598426.jpg").getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());
    }

}