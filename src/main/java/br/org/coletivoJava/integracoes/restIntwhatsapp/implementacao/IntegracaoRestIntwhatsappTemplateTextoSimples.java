package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappTemplate;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.templates.ComponenteTemplate;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.templates.MensagemTemplate;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.templates.ParametroTemplate;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappTemplate;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

@InfoIntegracaoRestIntwhatsappTemplate(tipo = FabApiRestIntWhatsappTemplate.TEMPLATE_TEXTO_SIMPLES)
public class IntegracaoRestIntwhatsappTemplateTextoSimples
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappTemplateTextoSimples(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappTemplate.TEMPLATE_TEXTO_SIMPLES,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        MensagemTemplate mensagemTemplate = (MensagemTemplate) parametros.get(2);

        JsonArrayBuilder componentes = Json.createArrayBuilder();

        for (ComponenteTemplate componente : mensagemTemplate.getComponentes()) {
            JsonArrayBuilder parametrosJson = Json.createArrayBuilder();

            for (ParametroTemplate parametro : componente.getParametros()) {
                JsonObjectBuilder parametroObj = Json.createObjectBuilder()
                        .add("type", parametro.getTipo());
                if (parametro.getTipo().equals("text")) {
                    parametroObj.add("text", parametro.getValor());
                }
                parametrosJson.add(parametroObj);
            }
            JsonObject componenteJson = Json.createObjectBuilder()
                    .add("type", componente.getTipo())
                    .add("parameters", parametrosJson)
                    .build();
            componentes.add(componenteJson);
        }

        JsonObject template = Json.createObjectBuilder()
                .add("name", mensagemTemplate.getNome())
                .add("language", Json.createObjectBuilder()
                        .add("code", "pt_BR"))
                .add("components", componentes)
                .build();
        JsonObject message = Json.createObjectBuilder()
                .add("messaging_product", "whatsapp")
                .add("to", telefone)
                .add("type", "template")
                .add("template", template)
                .build();

        System.out.println("JSONNN: " + message.toString());
        return message.toString();
    }


    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratamentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }
}