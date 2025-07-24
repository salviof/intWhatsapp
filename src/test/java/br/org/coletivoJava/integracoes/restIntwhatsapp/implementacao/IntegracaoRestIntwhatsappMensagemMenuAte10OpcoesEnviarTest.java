package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu.ItemMenuWhatsapp;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu.MenuWhatsapp;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class IntegracaoRestIntwhatsappMensagemMenuAte10OpcoesEnviarTest {

    @Test
    public void testeEnvioMensagemMenu() {

        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getGestaoToken();
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }

        List<ItemMenuWhatsapp> listaDeItens = new ArrayList<>();
        ItemMenuWhatsapp item1 = new ItemMenuWhatsapp();
        item1.setId("ajuda_1");
        item1.setTitulo("Suporte Técnico");
        item1.setDescricao("Falar com um atendente");

        ItemMenuWhatsapp item2 = new ItemMenuWhatsapp();
        item2.setId("ajuda_2");
        item2.setTitulo("Financeiro");
        item2.setDescricao("Ver boletos e pagamentos");

        listaDeItens.add(item1);
        listaDeItens.add(item2);
        MenuWhatsapp menu = new MenuWhatsapp();
        menu.setItensMenu(listaDeItens);

        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ATE_10_OPCOES_ENVIAR.getAcao(codigoTelefoneOrigemMensagem, "5531986831481", menu).getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());

    }
}
