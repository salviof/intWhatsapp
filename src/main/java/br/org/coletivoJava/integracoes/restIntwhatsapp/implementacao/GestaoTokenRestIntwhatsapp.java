package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenChaveUnica;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.TokenDeAcessoExternoSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR)
public class GestaoTokenRestIntwhatsapp extends GestaoTokenChaveUnica {

    private ConfigModulo config = SBCore.getConfigModulo(FabConfigApiWhatsapp.class);

    @Override
    public boolean validarToken() {
        return false;
    }

    @Override
    public String getToken() {
        String tk = super.getToken();
        if (tk == null) {
            gerarNovoToken();
            return super.getToken();
        } else {
            return tk;
        }

    }

    @Override
    public ItfTokenDeAcessoExterno loadTokenArmazenado() {
        return null;
    }

    public GestaoTokenRestIntwhatsapp(
            final FabTipoAgenteClienteApi pTipoAgente, final ComoUsuario pUsuario) {
        super(FabApiRestIntWhatsappMensagem.class, pTipoAgente, pUsuario);
    }

    @Override
    public ItfTokenDeAcessoExterno gerarNovoToken() {
        String token = config.getPropriedade(FabConfigApiWhatsapp.TOKEN_ACESSO);
        setToken(new TokenDeAcessoExternoSimples(token));
        return getTokenCompleto();

    }

}
