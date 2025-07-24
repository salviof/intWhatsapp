package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMidia;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappArquivoMidia;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

import java.util.Map;

@InfoIntegracaoRestIntwhatsappMidia(tipo = FabApiRestIntWhatsappArquivoMidia.ARQUIVO_PDF_ENVIO)
public class IntegracaoRestIntwhatsappArquivoPdfEnvio
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappArquivoPdfEnvio(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappArquivoMidia.ARQUIVO_PDF_ENVIO, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        byte[] arquivo = (byte[]) parametros.get(2);
        String nomeArquivo = (String) parametros.get(3);
        String codigoMetaArquivo;

        try {
            codigoMetaArquivo = gerarCodigoMetaArquivo(arquivo, nomeArquivo, "application/pdf");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "{\n" +
                "  \"messaging_product\": \"whatsapp\",\n" +
                "  \"to\": \"" + telefone + "\",\n" +
                "  \"type\": \"document\",\n" +
                "  \"document\": {\n" +
                "    \"id\": \"" + codigoMetaArquivo + "\",\n" +
                "    \"filename\": \"" + nomeArquivo + "\"\n" +
                "  }\n" +
                "}";
    }

    @Override
    public Map<String, String> gerarCabecalho() {
        Map<String, String> cabecalho = super.gerarCabecalho();
        cabecalho.put("content-type", "application/x-www-form-urlencoded");
        return cabecalho;
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