package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMidia;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappArquivoMidia;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntwhatsappMidia(tipo = FabApiRestIntWhatsappArquivoMidia.ARQUIVO_IMAGEM_ENVIO)
public class IntegracaoRestIntwhatsappArquivoImagemEnvio
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappArquivoImagemEnvio(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappArquivoMidia.ARQUIVO_IMAGEM_ENVIO,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        String telefone = (String) parametros.get(1);
        String linkImagem = (String) parametros.get(2);

        return "{\n" +
                "  \"messaging_product\": \"whatsapp\",\n" +
                "  \"to\": \"" + telefone + "\",\n" +
                "  \"type\": \"image\",\n" +
                "  \"image\": {\n" +
                "    \"link\": \"" + linkImagem + "\",\n" +
                "    \"caption\": \"Aqui está a imagem teste samuel\"\n" +
                "  }\n" +
                "}";
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratamentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }
}