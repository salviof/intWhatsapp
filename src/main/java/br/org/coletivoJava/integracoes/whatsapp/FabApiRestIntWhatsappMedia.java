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
public enum FabApiRestIntWhatsappMedia implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/{0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"MEDIA_ID"},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/reference/media",
            adicionarAutenticacaoBearer = true)
    MEDIA_GET_URL,
    ///
    @InfoConsumoRestService(getPachServico = "/{0}/media",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoEnviada = FabTipoArquivoImportacao.JSON,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"FROM_PHONE_NUMBER_ID", "MEDIA_ID"},
            urlDocumentacao = "https://developers.facebook.com/docs/whatsapp/cloud-api/reference/media",
            adicionarAutenticacaoBearer = true)
    MEDIA_POST_NOVA_MEDIA,

}
