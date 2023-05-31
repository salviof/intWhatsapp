/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.coletivoJava.integracoes.matrixChat;

import br.org.coletivoJava.integracoes.matrixChat.config.FabConfigApiMatrixChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.importacao.FabTipoArquivoImportacao;

/**
 *
 * @author salvio
 */
@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://docs.galaxpay.com.br/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigApiMatrixChat.NOME_INTEGRACAO,
        configuracao = FabConfigApiMatrixChat.class
)
public enum FabApiRestIntMatrixChatUsuarios implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v2/users/{0}",
            tipoConexao = FabTipoConexaoRest.PUT,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"useride"},
            parametrosPost = {"username", "email", "password"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html",
            adicionarAutenticacaoBearer = true)
    USUARIO_CRIAR,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v2/users/{0}",
            tipoConexao = FabTipoConexaoRest.PUT,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"useride"},
            parametrosPost = {"username", "password"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html")
    USUARIO_ATUALIZAR_SENHA,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v2/users/{0}",
            tipoConexao = FabTipoConexaoRest.PUT,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"useride"},
            parametrosPost = {"username", "email", "password"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html",
            adicionarAutenticacaoBearer = true)
    USUARIO_ATUALIZAR,
    USUARIO_REMOVER,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v2/users/{0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"IDuSUARIO"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html",
            adicionarAutenticacaoBearer = true)
    USUARIO_OBTER_DADOS,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v1/threepid/email/users/{0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"emailusuario"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html",
            adicionarAutenticacaoBearer = true)
    USUARIO_OBTER_DADOS_BY_EMAIL,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v1/rooms/{0}/members",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"ID_GRUPO"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/latest/admin_api/rooms.html",
            adicionarAutenticacaoBearer = true)
    USUARIOS_DA_SALA,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v2/users/{0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"IDuSUARIO"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html",
            adicionarAutenticacaoBearer = true)
    USUARIOS_LISTAGEM,
    @InfoConsumoRestService(getPachServico = "/_matrix/client/v3/presence/{0}/status",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"IDuSUARIO"},
            urlDocumentacao = "https://www.matrix.org/docs/api/#get-/_matrix/client/v3/presence/-userId-/status",
            adicionarAutenticacaoBearer = true)
    USUARIOS_STATUS,

}
