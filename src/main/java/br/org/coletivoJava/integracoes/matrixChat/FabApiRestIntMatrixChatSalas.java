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
public enum FabApiRestIntMatrixChatSalas implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v1/rooms?search_term={0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"codigoGrupo"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/latest/admin_api/rooms.html#list-room-api",
            adicionarAutenticacaoBearer = true)
    SALA_ENCONTRAR_POR_ID,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v1/rooms?search_term={0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"useride"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/latest/admin_api/rooms.html#list-room-api",
            adicionarAutenticacaoBearer = true)
    SALA_ENCONTRAR_POR_NOME,
    @InfoConsumoRestService(getPachServico = "/_matrix/client/r0/createRoom",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosPost = {"apelido", "nome", "topico"},
            urlDocumentacao = "https://ma1uta.github.io/spec/client_server/unstable.html#post-matrix-client-r0-createroom",
            adicionarAutenticacaoBearer = true)
    SALA_CRIAR,
    SALA_EXLUIR,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v2/users/{0}",
            tipoConexao = FabTipoConexaoRest.PUT,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"useride"},
            parametrosPost = {"username", "email", "password"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html",
            adicionarAutenticacaoBearer = true)
    SALA_ATUALIZAR,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v1/join/{0}",
            tipoConexao = FabTipoConexaoRest.POST,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"useride"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/latest/admin_api/room_membership.html",
            adicionarAutenticacaoBearer = true)
    SALA_ADICIONAR_USUARIO,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v2/users/{0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"IDuSUARIO"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html",
            adicionarAutenticacaoBearer = true)
    SALA_REMOVER_USUARIO,
    @InfoConsumoRestService(getPachServico = "/_synapse/admin/v2/users/{0}",
            tipoConexao = FabTipoConexaoRest.GET,
            tipoInformacaoRecebida = FabTipoArquivoImportacao.JSON,
            parametrosGet = {"IDuSUARIO"},
            urlDocumentacao = "https://matrix-org.github.io/synapse/v1.59/admin_api/user_admin_api.html",
            adicionarAutenticacaoBearer = true)
    USUARIO_DEFINIR_ADMIN

}
