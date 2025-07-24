package br.org.coletivoJava.integracoes.whatsapp;

import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao.FabTipoArquivoImportacao;

@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigApiWhatsapp.NOME_INTEGRACAO,
        configuracao = FabConfigApiWhatsapp.class
)
public enum FabApiRestIntWhatsappArquivoMidia implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {""},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/reference/media/?utm_source=chatgpt.com",
            adicionarAutenticacaoBearer = true)
    ARQUIVO_PDF_ENVIO,

    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {""},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/messages/image-messages/?utm_source=chatgpt.com",
            adicionarAutenticacaoBearer = true)
    ARQUIVO_IMAGEM_ENVIO
}
