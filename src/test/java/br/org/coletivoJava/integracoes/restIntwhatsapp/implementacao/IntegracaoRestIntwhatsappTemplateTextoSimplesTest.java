package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.templates.ComponenteTemplate;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.templates.MensagemTemplate;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.templates.ParametroTemplate;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappTemplate;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class IntegracaoRestIntwhatsappTemplateTextoSimplesTest {

    @Test
    public void templateTextoSimplesTest() {
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        ItfTokenGestao tokenEcontrarById = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR.getGestaoToken();
        String codigoTelefoneOrigemMensagem = FabConfigApiWhatsapp.CODIGO_USUARIO.getValorParametroSistema();

        if (!tokenEcontrarById.isTemTokemAtivo()) {
            tokenEcontrarById.gerarNovoToken();
        }

        List<ComponenteTemplate> listaDeComponentes = new ArrayList<>();
        List<ParametroTemplate> listaParametros = new ArrayList<>();
        ParametroTemplate parametro1 = new ParametroTemplate();

        parametro1.setTipo("text");
        parametro1.setValor("Teste 1 template simples COM HEADER");

        listaParametros.add(parametro1);

        ComponenteTemplate componente1 = new ComponenteTemplate();
        componente1.setTipo("body");
        componente1.setParametros(listaParametros);

        listaDeComponentes.add(componente1);

        MensagemTemplate msgTemplate = new MensagemTemplate();
        msgTemplate.setComponentes(listaDeComponentes);
        msgTemplate.setNome("pedido_contato_cliente");

        ItfRespostaWebServiceSimples resposta = FabApiRestIntWhatsappTemplate.TEMPLATE_TEXTO_SIMPLES.getAcao(codigoTelefoneOrigemMensagem, "31984178550", msgTemplate).getResposta();
        assertTrue("Erro acessando api de envio: \n" + resposta.getRespostaTexto(), resposta.isSucesso());

    }

}
