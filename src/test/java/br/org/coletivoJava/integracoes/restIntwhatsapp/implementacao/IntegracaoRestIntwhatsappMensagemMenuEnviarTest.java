package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class IntegracaoRestIntwhatsappMensagemMenuEnviarTest {
    @Test
    public void testeEnvioMensagemMenu() {

        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getGestaoToken();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }

        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ENVIAR.getAcao("5531986831481", "servico_1", "Consultar servico", "descricao teste").getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());

    }
}