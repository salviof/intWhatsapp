package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.mensagem.MensagemSimplesEnvioWhatsapp;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
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
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        MensagemSimplesEnvioWhatsapp mensagem = (MensagemSimplesEnvioWhatsapp) parametros.get(2);
        StringBuilder mensagemBuilder = new StringBuilder();
        if (mensagem.isTemCabecalho()) {
            mensagemBuilder.append("*");
            mensagemBuilder.append(mensagem.getCabecalho());
            mensagemBuilder.append("*\n\n");
            mensagemBuilder.append(mensagem.getCorpo());
        }
        if (mensagem.isTemRodape()) {
            mensagemBuilder.append("\n\n_");
            mensagemBuilder.append(mensagem.getRodape());
            mensagemBuilder.append("_");
        }
        try {
            JsonObjectBuilder corpoJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                    "messaging_product", "whatsapp",
                    "recipient_type", "individual",
                    "to", telefone,
                    "type", "Text"
            );
            if (mensagem.isTemCabecalho()) {
                corpoJson.add("header", mensagem.getCabecalho());
            }

            JsonObjectBuilder mensagemJson = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor(
                    "preview_url", false,
                    "body", mensagemBuilder.toString()
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
        UtilSBApiWhatsapp.gerarTratamentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }

    @Override
    public Map<String, String> gerarCabecalho() {
        Map<String, String> cabecalho = super.gerarCabecalho(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return cabecalho;
    }

}
