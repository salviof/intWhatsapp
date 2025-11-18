package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_REACAO)
public class IntegracaoRestIntwhatsappMensagemReacao
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemReacao(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_REACAO, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        String idMensagem = (String) parametros.get(2);
        String emoji = (String) parametros.get(3);

        String corpo = "{\n" +
                "  \"messaging_product\": \"whatsapp\",\n" +
                "  \"recipient_type\": \"individual\",\n" +
                "  \"to\": \"" + telefone + "\",\n" +
                "  \"type\": \"reaction\",\n" +
                "  \"reaction\": {\n" +
                "    \"message_id\": \"" + idMensagem + "\",\n" +
                "    \"emoji\": \"" + emoji + "\"\n" +
                "  }\n" +
                "}";

        return corpo;
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratamentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }
}