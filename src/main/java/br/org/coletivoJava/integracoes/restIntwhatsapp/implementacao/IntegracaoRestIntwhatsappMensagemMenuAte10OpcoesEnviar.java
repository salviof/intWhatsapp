package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu.Menu;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ATE_10_OPCOES_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemMenuAte10OpcoesEnviar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemMenuAte10OpcoesEnviar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ATE_10_OPCOES_ENVIAR,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        Menu menu = (Menu) parametros.get(2);
        menu.setItensMenu(menu.getItensMenu());

        JsonObjectBuilder interactiveBuilder;
        if (menu.isCompativelComMenuSimples()) {
            JsonArrayBuilder botoesBuilder = Json.createArrayBuilder();
            menu.getItensMenu().stream()
                    .limit(3)
                    .forEach(item -> botoesBuilder.add(Json.createObjectBuilder()
                            .add("type", "reply")
                            .add("reply", Json.createObjectBuilder()
                                    .add("id", item.getId())
                                    .add("title", item.getTitulo())
                            )
                    ));
            interactiveBuilder = Json.createObjectBuilder()
                    .add("type", "button")
                    .add("body", Json.createObjectBuilder()
                            .add("text", "Como posso ajudar?")
                    )
                    .add("action", Json.createObjectBuilder()
                            .add("buttons", botoesBuilder)
                    );
        } else {
            JsonArrayBuilder rowsBuilder = Json.createArrayBuilder();
            menu.getItensMenu()
                    .stream()
                    .forEach(item -> rowsBuilder.add(Json.createObjectBuilder()
                            .add("id", item.getId())
                            .add("title", item.getTitulo())
                            .add("description", item.getDescricao())
                    ));

            JsonArrayBuilder sectionsBuilder = Json.createArrayBuilder()
                    .add(Json.createObjectBuilder()
                            .add("title", "Serviços")
                            .add("rows", rowsBuilder)
                    );
            interactiveBuilder = Json.createObjectBuilder()
                    .add("type", "list")
                    .add("header", Json.createObjectBuilder()
                            .add("type", "text")
                            .add("text", "Escolha uma opção:")
                    )
                    .add("body", Json.createObjectBuilder()
                            .add("text", "Como posso te ajudar?")
                    )
                    .add("footer", Json.createObjectBuilder()
                            .add("text", "Toque para selecionar")
                    )
                    .add("action", Json.createObjectBuilder()
                            .add("button", "Ver opções")
                            .add("sections", sectionsBuilder)
                    );
        }

        JsonObject message = Json.createObjectBuilder()
                .add("messaging_product", "whatsapp")
                .add("to", telefone)
                .add("type", "interactive")
                .add("interactive", interactiveBuilder)
                .build();

        System.out.println("jsond: " + message);

        return message.toString();
//        return "{\n"
//                + "  \"messaging_product\": \"whatsapp\",\n"
//                + "  \"to\": \"5531984178550\",\n"
//                + "  \"type\": \"interactive\",\n"
//                + "  \"interactive\": {\n"
//                + "    \"type\": \"button\",\n"
//                + "    \"body\": {\n"
//                + "      \"text\": \"Como posso te ajudar?\"\n"
//                + "    },\n"
//                + "    \"action\": {\n"
//                + "      \"buttons\": [\n"
//                + "        {\n"
//                + "          \"type\": \"reply\",\n"
//                + "          \"reply\": {\n"
//                + "            \"id\": \"btn_limpeza\",\n"
//                + "            \"title\": \"Limpeza\",\n"
//                + "            \"description\": \"descricao Teste\"\n"
//                + "          }\n"
//                + "        },\n"
//                + "        {\n"
//                + "          \"type\": \"reply\",\n"
//                + "          \"reply\": {\n"
//                + "            \"id\": \"btn_reparos\",\n"
//                + "            \"title\": \"Reparos\"\n"
//                + "          }\n"
//                + "        },\n"
//                + "        {\n"
//                + "          \"type\": \"reply\",\n"
//                + "          \"reply\": {\n"
//                + "            \"id\": \"btn_suporte\",\n"
//                + "            \"title\": \"Suporte\"\n"
//                + "          }\n"
//                + "        }\n"
//                + "      ]\n"
//                + "    }\n"
//                + "  }\n"
//                + "}";
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratmentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }

}
