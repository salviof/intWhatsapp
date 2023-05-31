package br.org.coletivoJava.integracoes.restIntmatrixchat.implementacao;

import br.org.coletivoJava.integracoes.restIntmatrixchat.api.InfoIntegracaoRestIntmatrixchatUsuarios;
import br.org.coletivoJava.integracoes.matrixChat.FabApiRestIntMatrixChatUsuarios;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreGravatar;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@InfoIntegracaoRestIntmatrixchatUsuarios(tipo = FabApiRestIntMatrixChatUsuarios.USUARIO_ATUALIZAR)
public class IntegracaoRestIntmatrixchatUsuarioAtualizar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntmatrixchatUsuarioAtualizar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntMatrixChatUsuarios.USUARIO_ATUALIZAR, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        JsonObjectBuilder jsonBUilder;
        try {
            String codUsuario = (String) parametros[0];
            String usuario = (String) getParametros()[0].toString().substring(0, getParametros()[0].toString().indexOf(":"));
            String nome = (String) getParametros()[1].toString().substring(0, getParametros()[0].toString().indexOf(":"));
            String email = (String) getParametros()[2];
            String senha = (String) getParametros()[3];

            jsonBUilder = UtilSBCoreJson.
                    getJsonBuilderBySequenciaChaveValor(
                            //      "name", usuarioFormatado,
                            "displayname", nome,
                            "password", senha,
                            "avatar_url", UtilSBCoreGravatar.getGravatarUrl(email, 80),
                            "admin", false,
                            "deactivated", false
                    //,"access_token", getTokenGestao().getToken()
                    );

            JsonObject threepid = UtilSBCoreJson.getJsonObjectBySequenciaChaveValor("medium", "email", "address", email);
            JsonArrayBuilder threepidsBuilder = Json.createArrayBuilder();
            threepidsBuilder.add(threepid);

            jsonBUilder.add("threepids", threepidsBuilder.build());
        } catch (ErroProcessandoJson ex) {
            throw new UnsupportedOperationException("Parametros Iválidos");
        }
        return UtilSBCoreJson.getTextoByJsonObjeect(jsonBUilder.build());
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {

        return UtilMatrixApiServer.gerarRespostaWSTratamentoFino(pRespostaWSSemTratamento);
    }
}
