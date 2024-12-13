package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
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
import jakarta.json.JsonObjectBuilder;
import java.util.Map;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_AUDIO_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemAudioEnviar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemAudioEnviar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_AUDIO_ENVIAR, pTipoAgente,
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
        String codigoMedia = (String) parametros.get(1);
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
        UtilSBApiWhatsapp.gerarTratmentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }

    @Override
    public Map<String, String> gerarCabecalho() {
        Map<String, String> cabecalho = super.gerarCabecalho(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return cabecalho;
    }
}
