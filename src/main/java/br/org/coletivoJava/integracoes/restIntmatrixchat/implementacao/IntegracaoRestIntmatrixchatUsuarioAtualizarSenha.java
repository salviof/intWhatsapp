package br.org.coletivoJava.integracoes.restIntmatrixchat.implementacao;

import br.org.coletivoJava.integracoes.restIntmatrixchat.api.InfoIntegracaoRestIntmatrixchatUsuarios;
import br.org.coletivoJava.integracoes.matrixChat.FabApiRestIntMatrixChatUsuarios;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.json.ErroProcessandoJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import jakarta.json.JsonObjectBuilder;

@InfoIntegracaoRestIntmatrixchatUsuarios(tipo = FabApiRestIntMatrixChatUsuarios.USUARIO_ATUALIZAR_SENHA)
public class IntegracaoRestIntmatrixchatUsuarioAtualizarSenha
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntmatrixchatUsuarioAtualizarSenha(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntMatrixChatUsuarios.USUARIO_ATUALIZAR_SENHA,
                pTipoAgente, pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        JsonObjectBuilder jsonBUilder;
        try {
            String codUsuario = (String) parametros[0];

            String senha = (String) getParametros()[1];

            jsonBUilder = UtilSBCoreJson.
                    getJsonBuilderBySequenciaChaveValor(
                            //      "name", usuarioFormatado,
                            "password", senha,
                            "deactivated", false
                    //,"access_token", getTokenGestao().getToken()
                    );

        } catch (ErroProcessandoJson ex) {
            throw new UnsupportedOperationException("Parametros Iválidos");
        }
        return UtilSBCoreJson.getTextoByJsonObjeect(jsonBUilder.build());
    }

}
