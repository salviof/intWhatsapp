package br.org.coletivoJava.integracoes.whatsapp;

import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ComoFabricaIntegracaoRest;
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
public enum FabApiRestIntWhatsappTemplate implements ComoFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {""},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/api/messages/message-templates/media-message-templates/",
            adicionarAutenticacaoBearer = true)
    TEMPLATE_TEXTO_SIMPLES,
    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {""},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/api/messages/message-templates/media-message-templates/",
            adicionarAutenticacaoBearer = true)
    TEMPLATE_MEDIA_BOTAO,
    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {""},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/api/messages/message-templates/media-message-templates/",
            adicionarAutenticacaoBearer = true)
    TEMPLATE_MEDIA,
//    TEMPLATE_FOOTER,
//    TEMPLATE_BOTAO_LINK,
//    TEMPLATE_COMPLEXO
    //TEMPLATE_INTERATIVO
    //TEMPLATE_CUSTOMIZADO


}
