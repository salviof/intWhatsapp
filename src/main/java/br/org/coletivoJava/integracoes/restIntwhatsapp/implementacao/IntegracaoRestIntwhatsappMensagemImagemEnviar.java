package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_IMAGEM_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemImagemEnviar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMensagemImagemEnviar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
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
            codigoMetaArquivo = gerarCodigoMetaArquivo(arquivo, nomeArquivo, getTipoImagem(arquivo));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "{\n" +
                "  \"messaging_product\": \"whatsapp\",\n" +
                "  \"to\": \"" + telefone + "\",\n" +
                "  \"type\": \"image\",\n" +
                "  \"image\": {\n" +
                "    \"id\": \"" + codigoMetaArquivo + "\",\n" +
                "    \"caption\": \"" + "\"\n" +
                "  }\n" +
                "}";
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratamentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }

    /**
     * O WhatsApp só aceita imagem JPEG ou PNG; o tipo informado no upload tem
     * de bater com o conteúdo.
     */
    private String getTipoImagem(byte[] pArquivo) {
        if (pArquivo != null && pArquivo.length > 4
                && (pArquivo[0] & 0xFF) == 0x89 && pArquivo[1] == 'P' && pArquivo[2] == 'N' && pArquivo[3] == 'G') {
            return "image/png";
        }
        return "image/jpeg";
    }

    private String gerarCodigoMetaArquivo(byte[] pArquivo, String pNomeArquivo, String pTipoArquivo) throws Exception {
        return UtilSBApiWhatsapp.mediaUpload(pArquivo, pNomeArquivo, pTipoArquivo);
    }
}