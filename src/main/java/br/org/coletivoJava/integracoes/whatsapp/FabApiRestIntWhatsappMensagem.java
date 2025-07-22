/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.coletivoJava.integracoes.whatsapp;

import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao.FabTipoArquivoImportacao;

/**
 * @author salvio
 */
@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigApiWhatsapp.NOME_INTEGRACAO,
        configuracao = FabConfigApiWhatsapp.class
)
public enum FabApiRestIntWhatsappMensagem implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {"username", "message"},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/get-started#sent-test-message",
            adicionarAutenticacaoBearer = true)
    MENSAGEM_ENVIAR,
    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {"username", "message"},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/get-started#sent-test-message",
            adicionarAutenticacaoBearer = true)
    MENSAGEM_AUDIO_ENVIAR,
    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {"username", "email", "password"},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/api/messages/message-templates/media-message-templates/",
            adicionarAutenticacaoBearer = true)
    MENSAGEM_TEMPLATE_SIMPLES,
    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {"DESTINO_WA_ID"},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/get-started#sent-test-message",
            adicionarAutenticacaoBearer = true)
    MENSAGEM_FLOW,
    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {"header, title, description"},
            adicionarAutenticacaoBearer = true)
    MENSAGEM_MENU_ATE_10_OPCOES_ENVIAR,

    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {"message_id", "emoji"},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/messages/reaction-messages/",
            adicionarAutenticacaoBearer = true)
    MENSAGEM_REACAO,

    @InfoConsumoRestService(getPachServico = "/{0}/messages",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID"},
            parametrosPost = {"telefone", "message_id", "mensagemRespoosta"},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/reference/messages#text-messages",
            adicionarAutenticacaoBearer = true)
    MENSAGEM_CONTEXTO_RESPOSTA,


}
