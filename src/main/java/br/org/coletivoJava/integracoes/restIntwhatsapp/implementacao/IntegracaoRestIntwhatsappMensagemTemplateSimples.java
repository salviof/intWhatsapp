package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.FabTipoParametrnoWhatsapp;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.ItfParametroMensagemWhatsapp;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringTelefone;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES)
public class IntegracaoRestIntwhatsappMensagemTemplateSimples
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemTemplateSimples(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_TEMPLATE_SIMPLES,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        String url = super.gerarUrlRequisicao();
        ConfigModulo config = SBCore.getConfigModulo(FabConfigApiWhatsapp.class);
        url = url.replace("[FROM_PHONE_NUMBER_ID]", config.getPropriedade(FabConfigApiWhatsapp.CODIGO_USUARIO));
        return url;
    }

    private final static JsonObject linguagem = UtilSBCoreJson.getJsonObjectByTexto("{\n"
            + "      \"code\": \"pt_BR\"\n"
            + "    }");

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(0);
        telefone = UtilSBCoreStringTelefone.gerarCeluarWhatasapp(telefone);
        String template = (String) parametros.get(1);
        List<ItfParametroMensagemWhatsapp> prsTemnplate = null;
        if (parametros.size() > 2) {
            prsTemnplate = (List) parametros.get(2);
        }
        try {
            JsonObjectBuilder corpoJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                    "messaging_product", "whatsapp",
                    "recipient_type", "individual",
                    "to", telefone,
                    "type", "template"
            );

            JsonObjectBuilder templateJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                    "name", template
            );
            templateJson.add("language", linguagem);

            boolean temHeaderPr = false;
            boolean temCorpoParametro = false;
            if (prsTemnplate != null) {
                temHeaderPr = prsTemnplate.stream().filter(pr -> pr.isCabecalho()).findFirst().isPresent();
                temCorpoParametro = prsTemnplate.stream().filter(pr -> !pr.isCabecalho()).findFirst().isPresent();
                JsonArrayBuilder componentes = Json.createArrayBuilder();
                if (temHeaderPr) {
                    JsonObjectBuilder header = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("type", "header");
                    JsonArrayBuilder parametrosHeader = gerarParametrosBodyJson(prsTemnplate, true);

                    header.add("parameters", parametrosHeader);
                    componentes.add(header);
                }
                if (temCorpoParametro) {
                    JsonObjectBuilder corpo = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("type", "body");

                    JsonArrayBuilder parametrosCorpo = gerarParametrosBodyJson(prsTemnplate, false);
                    corpo.add("parameters", parametrosCorpo);
                    componentes.add(corpo);
                }
                List<ItfParametroMensagemWhatsapp> parametrosBotao = getparametrosDeBotao(prsTemnplate);

                for (ItfParametroMensagemWhatsapp pr : parametrosBotao) {
                    componentes.add(gerarParametrosBotaoJson(pr));
                }

                templateJson.add("components", componentes);
            }
            corpoJson.add("template", templateJson);

            String corpo = UtilSBCoreJson.getTextoByJsonObjeect(corpoJson.build());
            System.out.println(corpo);
            return corpo;
        } catch (ErroProcessandoJson ex) {
            throw new UnsupportedOperationException("erro criando json de ação");
        }

    }

    public static JsonArrayBuilder gerarParametrosBodyJson(List<ItfParametroMensagemWhatsapp> pParametros, boolean pCabecalho) {
        JsonArrayBuilder parametros = Json.createArrayBuilder();
        pParametros.stream().filter(pr -> pr.isCabecalho() == pCabecalho).forEach(new Consumer<ItfParametroMensagemWhatsapp>() {
            @Override
            public void accept(ItfParametroMensagemWhatsapp pr) {
                try {
                    JsonObjectBuilder prJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("type", pr.getTipoParametroWtzap());
                    FabTipoParametrnoWhatsapp tipo = FabTipoParametrnoWhatsapp.getTipoByString(pr.getTipoParametroWtzap());
                    switch (tipo) {
                        case TEXT:
                            prJson.add("text", pr.getValor());
                            parametros.add(prJson);
                            break;
                        case DOCUMENT:
                            JsonObjectBuilder prJsonDocument = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("filename", pr.getNome(), "link", pr.getValor());
                            prJson.add("document", prJsonDocument);
                            parametros.add(prJson);
                            break;
                        case IMAGE:
                            JsonObjectBuilder prJsonImagem = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("name", pr.getNome(), "link", pr.getValor());
                            prJson.add("image", prJsonImagem);
                            parametros.add(prJson);
                            break;
                        case VIDEO:
                            //"mime_type", "video/mp4",
                            JsonObjectBuilder prJsoVideo = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("link", pr.getValor());
                            prJson.add("video", prJsoVideo);
                            parametros.add(prJson);
                            break;
                        case LOCATION:

                            break;
                        case RODAPE_BOTAO:
                            break;

                        default:
                            throw new AssertionError();
                    }

                } catch (ErroProcessandoJson ex) {
                    throw new UnsupportedOperationException("Erro criando parametro" + pr.getCodigoParametro() + " Pr de cabeçalho?" + pr.isCabecalho());
                }
            }
        });
        return parametros;
    }

    public static List<ItfParametroMensagemWhatsapp> getparametrosDeBotao(List<ItfParametroMensagemWhatsapp> pParametros) {
        List<ItfParametroMensagemWhatsapp> resposta = new ArrayList<>();
        pParametros.stream().filter(pr -> pr.getTipoParametroWtzap().equals(FabTipoParametrnoWhatsapp.RODAPE_BOTAO.toString())).forEach(resposta::add);
        return resposta;
    }

    public static JsonObjectBuilder gerarParametrosBotaoJson(ItfParametroMensagemWhatsapp pParametro) {

        FabTipoParametrnoWhatsapp tipo = FabTipoParametrnoWhatsapp.getTipoByString(pParametro.getTipoParametroWtzap());
        JsonObjectBuilder prJson = null;
        switch (tipo) {

            case RODAPE_BOTAO:
            try {
                prJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("type", "button", "index", pParametro.getCodigoParametro(), "sub_type", "url");
                JsonObjectBuilder prUrlParametrourl = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("type", "text", "text", pParametro.getValor());
                JsonArrayBuilder parametros = Json.createArrayBuilder();
                parametros.add(prUrlParametrourl);
                prJson.add("parameters", parametros);

                return prJson;
            } catch (ErroProcessandoJson ex) {
                Logger.getLogger(IntegracaoRestIntwhatsappMensagemTemplateSimples.class.getName()).log(Level.SEVERE, null, ex);
            }

            /**
             * {
             * "type": "button", "index": "0", "sub_type": "url", "parameters":
             * [ { "type": "text", "text": "1Z999AA10123456784" } ] }
             *
             */
            //JsonObjectBuilder prBotao = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("index", "0", "sub_type", "url");
            }

        return prJson;
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratmentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }
}
