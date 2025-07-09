package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemMenuEnviar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemMenuEnviar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ENVIAR, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        String url = super.gerarUrlRequisicao();
        ConfigModulo config = SBCore.getConfigModulo(FabConfigApiWhatsapp.class);
        url = url.replace("[FROM_PHONE_NUMBER_ID]", config.getPropriedade(FabConfigApiWhatsapp.CODIGO_USUARIO));
        return url;
    }


    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(0);
//		String mensagem = (String) parametros.get(1);
        String id = (String) parametros.get(1);
        String titulo = (String) parametros.get(2);
        String descricao = (String) parametros.get(3);


        //talvez clcar uma lista aq para iterar dps (conferir)
        //melhorar os parametros

        JsonArrayBuilder linhaBuilder = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("id", id)
                        .add("title", titulo)
                        .add("description", descricao)
                );

        JsonArrayBuilder sectionBuilder = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add("title", "Serviços")
                        .add("rows", linhaBuilder));

        JsonObject message = Json.createObjectBuilder()
                .add("messaging_product", "whatsapp")
                .add("to", telefone)
                .add("type", "interactive")
                .add("interactive", Json.createObjectBuilder()
                        .add("type", "list")
                        .add("header", Json.createObjectBuilder()
                                .add("type", "text")
                                .add("text", "Escolha uma opção:"))
                        .add("body", Json.createObjectBuilder()
                                .add("text", "Como posso te ajudar?"))
                        .add("footer", Json.createObjectBuilder()
                                .add("text", "Toque para selecionar"))
                        .add("action", Json.createObjectBuilder()
                                .add("button", "Ver opções")
                                .add("sections", sectionBuilder)))
                .build();

        System.out.println("jsond: " + message);

        return message.toString();
    }

}