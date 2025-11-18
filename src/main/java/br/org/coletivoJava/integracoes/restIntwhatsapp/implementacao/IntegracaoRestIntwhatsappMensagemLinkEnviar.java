package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_LINK_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemLinkEnviar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemLinkEnviar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_LINK_ENVIAR, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        String descricao = (String) parametros.get(2);
        String acao = (String) parametros.get(3);
        String link = (String) parametros.get(4);
        String json = "{\n"
                + "  \"messaging_product\": \"whatsapp\",\n"
                + "  \"to\": \"" + telefone + "\",\n"
                + "  \"type\": \"text\",\n"
                + "  \"text\": {\n"
                + "    \"preview_url\": true,\n"
                + "    \"body\": \" \\uD83D\\uDD17 *" + descricao + "* \\uD83D\\uDD17 \\n\\n\\n " + link + " \\n\\n " + acao + " \""
                + "  }\n"
                + "}";
        return json;
    }

}
