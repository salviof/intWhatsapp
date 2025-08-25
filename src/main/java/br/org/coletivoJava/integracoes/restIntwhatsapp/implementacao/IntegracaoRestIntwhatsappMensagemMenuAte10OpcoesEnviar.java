package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu.MenuWhatsapp;
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
        MenuWhatsapp menu = (MenuWhatsapp) parametros.get(2);
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
                                    .add("title", limitarTitulo(item.getTitulo(), 20))
                            )
                    ));
            interactiveBuilder = Json.createObjectBuilder()
                    .add("type", "button")
                    .add("body", Json.createObjectBuilder()
                                    .add("text", menu.getMensagem().getCorpo())
//                                    .add("text", "Escolha uma opcao:") // ver isso aq dps
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
                            .add("text", menu.getMensagem().getCorpo())
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
    }


    private static String limitarTitulo(String pTitulo, int pLimiteCaracteres) {
        if (pTitulo == null) return "";
        if (pTitulo.length() <= pLimiteCaracteres) {
            return pTitulo;
        }
        return pTitulo.substring(0, pLimiteCaracteres - 3) + "...";

    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratamentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }

}
