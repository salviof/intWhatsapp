package br.org.coletivoJava.integracoes.restIntmatrixchat.implementacao;

import br.org.coletivoJava.integracoes.restIntmatrixchat.api.InfoIntegracaoRestIntmatrixchatUsuarios;
import br.org.coletivoJava.integracoes.matrixChat.FabApiRestIntMatrixChatUsuarios;
import br.org.coletivoJava.integracoes.matrixChat.config.FabConfigApiMatrixChat;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreDataHora;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.TokenDeAcessoExternoDinamico;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.TokenDeAcessoExternoSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.UtilSBApiRestClient;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenDinamico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@InfoIntegracaoRestIntmatrixchatUsuarios(tipo = FabApiRestIntMatrixChatUsuarios.USUARIO_CRIAR)
public class GestaoTokenRestIntmatrixchat extends GestaoTokenDinamico {

    private final ConfigModulo configuracao;
    private static TokenDeAcessoExternoSimples tokeanLoad;
    private String loginNomeUsuario;
    private String loginSenhaUsuario;
    private String userID;
    private String deviceId;
    private String homeServer;

    @Override
    public boolean validarToken() {

        return isTemTokemAtivo();
    }

    public GestaoTokenRestIntmatrixchat(
            final FabTipoAgenteClienteApi pTipoAgente, final ItfUsuario pUsuario) {
        super(FabApiRestIntMatrixChatUsuarios.class, pTipoAgente, pUsuario);
        configuracao = getConfig();
    }

    @Override
    public ItfTokenDeAcessoExterno gerarNovoToken() {
        String url = configuracao.getPropriedade(FabConfigApiMatrixChat.URL_MATRIX_SERVER) + "/_matrix/client/v3/login";

        String usuarioLogin = null;
        String senhaLogin = null;

        switch (getTipoAgente()) {
            case USUARIO:
                usuarioLogin = loginNomeUsuario;
                senhaLogin = loginSenhaUsuario;
                break;
            case SISTEMA:
                usuarioLogin = configuracao.getPropriedade(FabConfigApiMatrixChat.USUARIO_ADMIN);
                senhaLogin = configuracao.getPropriedade(FabConfigApiMatrixChat.SENHA_USUARIO_ADMIN);
                break;
            default:
                throw new AssertionError(getTipoAgente().name());

        }
        System.out.println("Efetuando login com " + usuarioLogin);
        JsonObjectBuilder buider;
        String corpo = "";
        try {
            buider = UtilSBCoreJson.getJsonBuilderBySequenciaChaveValor("type", "m.login.password");
            JsonObject tipoIdentificador = UtilSBCoreJson.getJsonObjectBySequenciaChaveValor("type", "m.id.user", "user", usuarioLogin);
            buider.add("identifier", tipoIdentificador);
            buider.add("password", senhaLogin);
            corpo = UtilSBCoreJson.getTextoByJsonObjeect(buider.build());
        } catch (ErroProcessandoJson ex) {
            Logger.getLogger(GestaoTokenRestIntmatrixchat.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (usuarioLogin != null) {
            RespostaWebServiceSimples resposta = UtilSBApiRestClient.getRespostaRest(url, FabTipoConexaoRest.POST, true,
                    new HashMap<>(), corpo);
            UtilMatrixApiServer.gerarRespostaWSTratamentoFino(resposta);
            if (resposta.isSucesso()) {
                JsonObject jsonArquivado = resposta.getRespostaComoObjetoJson();
                jsonArquivado = UtilSBCoreJson.getJsonObjectIncrementandoCampo(jsonArquivado, "dataHora", new Date().getTime());

                armazenarRespostaToken(UtilSBCoreJson.getTextoByJsonObjeect(jsonArquivado));
            } else {
                if (!SBCore.isEmModoProducao()) {
                    //  resposta.dispararMensagens();
                }
                //     SBCore.enviarAvisoAoUsuario("Usuário ou senha inválida, verifique suas credenciais em " + getConfig().getPropriedade(FabConfigApiMatrixChat.URL_MATRIX_SERVER));
            }
        }
        return loadTokenArmazenado();
    }

    @Override
    public ItfTokenDeAcessoExterno extrairToken(JsonObject pJson) {
        userID = pJson.getString("user_id");
        Date dataHoraExipira = UtilSBCoreDataHora.incrementaMinutos(new Date(), 5);
        if (pJson.containsKey("dataHora")) {

            Date dataHoraGeracaoToken = new Date((long) pJson.getJsonNumber("dataHora").longValue());
            dataHoraExipira = UtilSBCoreDataHora.incrementaHoras(dataHoraGeracaoToken, 6);
        }
        deviceId = pJson.getString("device_id");
        homeServer = pJson.getString("home_server");
        TokenDeAcessoExternoDinamico novoToken = new TokenDeAcessoExternoDinamico(pJson.getString("access_token"), dataHoraExipira);
        return novoToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getUserID() {
        return userID;
    }

    public String getHomeServer() {
        return homeServer;
    }

    public String getLoginNomeUsuario() {
        return loginNomeUsuario;
    }

    public void setLoginNomeUsuario(String loginNomeUsuario) {
        this.loginNomeUsuario = loginNomeUsuario;
    }

    public String getLoginSenhaUsuario() {
        return loginSenhaUsuario;
    }

    public void setLoginSenhaUsuario(String loginSenhaUsuario) {
        this.loginSenhaUsuario = loginSenhaUsuario;
    }

}
