package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_IMAGEM_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemImagemEnviar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemImagemEnviar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMensagem.MENSAGEM_IMAGEM_ENVIAR,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        byte[] arquivo = (byte[]) parametros.get(2);
        String nomeArquivo = (String) parametros.get(3);
        String codigoMetaArquivo;
        try {
            codigoMetaArquivo = gerarCodigoMetaArquivo(arquivo, nomeArquivo, "image/jpeg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "{\n" +
                "  \"messaging_product\": \"whatsapp\",\n" +
                "  \"to\": \"" + telefone + "\",\n" +
                "  \"type\": \"image\",\n" +
                "  \"image\": {\n" +
                "    \"link\": \"" + codigoMetaArquivo + "\",\n" +
                "    \"caption\": \"" + "\"\n" +
                "  }\n" +
                "}";
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratamentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }

    private String gerarCodigoMetaArquivo(byte[] pArquivo, String pNomeArquivo, String pTipoArquivo) throws Exception {
        return UtilSBApiWhatsapp.mediaUpload(pArquivo, pNomeArquivo, pTipoArquivo);
    }
}