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

@InfoIntegracaoRestIntmatrixchatUsuarios(tipo = FabApiRestIntMatrixChatUsuarios.USUARIO_CRIAR)
public class IntegracaoRestIntmatrixchatUsuarioCriar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntmatrixchatUsuarioCriar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntMatrixChatUsuarios.USUARIO_CRIAR, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        JsonObjectBuilder jsonBUilder;
        try {

            String usuario = (String) getParametros()[1];
            String nome = (String) getParametros()[2];
            String email = (String) getParametros()[3];
            String telefone = (String) getParametros()[4];
            String senha = (String) getParametros()[5];

            System.out.println("Criando usuário:");
            System.out.println("codigo: " + getParametros()[0]);
            System.out.println("nome: " + nome);
            System.out.println("email:" + email);
            System.out.println("telefone" + telefone);
            System.out.println("Senha:" + senha);

            jsonBUilder = UtilSBCoreJson.
                    getJsonBuilderBySequenciaChaveValor(
                            "username", usuario,
                            "displayname", nome,
                            "password", senha,
                            "avatar_url", UtilSBCoreGravatar.getGravatarUrl(email, 80),
                            "admin", false,
                            "deactivated", false
                    //,"access_token", getTokenGestao().getToken()
                    );

            JsonObject threepidEmail = UtilSBCoreJson.getJsonObjectBySequenciaChaveValor("medium", "email", "address", email);
            JsonArrayBuilder threepidsBuilder = Json.createArrayBuilder();
            threepidsBuilder.add(threepidEmail);
            if (telefone != null) {
                JsonObject threepidPhone = UtilSBCoreJson.getJsonObjectBySequenciaChaveValor("medium", "msisdn", "address", telefone);

                threepidsBuilder.add(threepidPhone);
            }
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
