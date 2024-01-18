package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_FLOW)
public class IntegracaoRestIntwhatsappMensagemFlow
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemFlow(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_FLOW, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String corpo = "{\n"
                + "    \"messaging_product\": \"whatsapp\",\n"
                + "    \"to\": \"{{customer-phone-number}}\",\n"
                + "    \"recipient_type\": \"individual\",\n"
                + "    \"type\": \"interactive\",\n"
                + "    \"interactive\": {\n"
                + "        \"type\": \"flow\",\n"
                + "        \"header\": {\n"
                + "            \"type\": \"text\",\n"
                + "            \"text\": \"<HEADER_TEXT>\"\n"
                + "        },\n"
                + "        \"body\": {\n"
                + "            \"text\": \"<BODY_TEXT>\"\n"
                + "        },\n"
                + "        \"footer\": {\n"
                + "            \"text\": \"<FOOTER_TEXT>\"\n"
                + "        },\n"
                + "        \"action\": {\n"
                + "            \"name\": \"flow\",\n"
                + "            \"parameters\": {\n"
                + "                \"flow_message_version\": \"3\",\n"
                + "                \"flow_action\": \"navigate\",\n"
                + "                \"flow_token\": \"<FLOW_TOKEN>\",\n"
                + "                \"flow_id\": \"{{flow-id}}\",\n"
                + "                \"flow_cta\": \"Open Flow!\",\n"
                + "                \"flow_action_payload\": {\n"
                + "                    \"screen\": \"<SCREEN_ID>\",\n"
                + "                    \"data\": {\n"
                + "                        \"<CUSTOM_KEY>\": \"<CUSTOM_VALUE>\"\n"
                + "                    }\n"
                + "                }\n"
                + "            }\n"
                + "        }\n"
                + "    }\n"
                + "}";
        return corpo;
    }

}
