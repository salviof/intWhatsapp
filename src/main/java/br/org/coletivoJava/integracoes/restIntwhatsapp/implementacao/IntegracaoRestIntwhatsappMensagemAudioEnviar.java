package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringTelefone;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;

import java.util.Map;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_AUDIO_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemAudioEnviar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemAudioEnviar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_AUDIO_ENVIAR, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        String codigoMedia = (String) parametros.get(2);
        telefone = UtilSBCoreStringTelefone.gerarCeluarWhatasapp(telefone);
        String corpo = "{\n"
                + "  \"messaging_product\": \"whatsapp\",\n"
                + "  \"recipient_type\": \"individual\",\n"
                + "  \"to\": \"" + telefone + "\",\n"
                + "  \"type\": \"audio\",\n"
                + "  \"audio\": {\n"
                + "    \"id\" : \"" + codigoMedia + "\"\n"
                + "  }\n"
                + "}".replace("<MEDIA_ID>", codigoMedia).replace("<WHATSAPP_USER_PHONE_NUMBER>", telefone);
        return corpo;

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

    private String gerarCodigoMetaArquivo(byte[] pArquivo, String pNomeArquivo, String pTipoArquivo) throws Exception {
        return UtilSBApiWhatsapp.mediaUpload(pArquivo, pNomeArquivo, pTipoArquivo);
    }
}
