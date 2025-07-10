package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu.ItemMenu;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu.Menu;
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

        List<ItemMenu> listaDeItens = new ArrayList<>();
        ItemMenu item1 = new ItemMenu();
        item1.setId("ajuda_1");
        item1.setTitulo("Suporte Técnico");
        item1.setDescricao("Falar com um atendente");

        ItemMenu item2 = new ItemMenu();
        item2.setId("ajuda_2");
        item2.setTitulo("Financeiro");
        item2.setDescricao("Ver boletos e pagamentos");

        listaDeItens.add(item1);
        listaDeItens.add(item2);
        Menu menu = new Menu();
        menu.setItensMenu(listaDeItens);

        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ATE_10_OPCOES_ENVIAR.getAcao(codigoTelefoneOrigemMensagem, "5531986831481", menu).getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());

    }
}
