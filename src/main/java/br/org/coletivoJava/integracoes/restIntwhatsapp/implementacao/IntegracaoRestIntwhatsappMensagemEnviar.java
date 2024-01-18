package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.JsonObjectBuilder;
import java.util.Map;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemEnviar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemEnviar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR, pTipoAgente,
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
        String mensagem = (String) parametros.get(1);
        try {
            JsonObjectBuilder corpoJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                    "messaging_product", "whatsapp",
                    "recipient_type", "individual",
                    "to", telefone,
                    "type", "Text"
            );
            JsonObjectBuilder mensagemJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                    "preview_url", false,
                    "body", mensagem
            );
            corpoJson.add("text", mensagemJson.build());

            String corpo = UtilSBCoreJson.getTextoByJsonObjeect(corpoJson.build());
            return corpo;
        } catch (ErroProcessandoJson ex) {
            throw new UnsupportedOperationException("erro criando json de ação");
        }

    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratmentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }

    @Override
    public Map<String, String> gerarCabecalho() {
        Map<String, String> cabecalho = super.gerarCabecalho(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return cabecalho;
    }

}
