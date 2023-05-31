package br.org.coletivoJava.integracoes.restIntmatrixchat.implementacao;

import br.org.coletivoJava.integracoes.restIntmatrixchat.api.InfoIntegracaoRestIntmatrixchatSalas;
import br.org.coletivoJava.integracoes.matrixChat.FabApiRestIntMatrixChatSalas;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntmatrixchatSalas(tipo = FabApiRestIntMatrixChatSalas.SALA_CRIAR)
public class IntegracaoRestIntmatrixchatSalaCriar
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntmatrixchatSalaCriar(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntMatrixChatSalas.SALA_CRIAR, pTipoAgente, pUsuario,
                pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {

        String apelidoSala = (String) parametros[0];//thepub
        String nomeSala = (String) parametros[1];//The Grand Duke Pub

        String corpo = "{\n"
                + "  \"preset\": \"public_chat\",\n"
                + "  \"room_alias_name\": \"" + apelidoSala + "\",\n"
                + "  \"name\": \"" + nomeSala + "\",\n"
                //        + "  \"topic\": \"" + topico + "\",\n"
                + "  \"creation_content\": {\n"
                + "    \"m.federate\": false\n"
                + "  }\n"
                + "}";
        return corpo;
    }

}
