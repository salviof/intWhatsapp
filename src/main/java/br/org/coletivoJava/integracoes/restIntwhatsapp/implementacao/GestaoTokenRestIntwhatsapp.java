package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.GestaoTokenChaveUnica;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenDeAcessoExterno;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_ENVIAR)
public class GestaoTokenRestIntwhatsapp extends GestaoTokenChaveUnica {

	@Override
	public boolean validarToken() {
		return false;
	}

	@Override
	public ItfTokenDeAcessoExterno loadTokenArmazenado() {
		return null;
	}

	public GestaoTokenRestIntwhatsapp(
			final FabTipoAgenteClienteApi pTipoAgente, final ItfUsuario pUsuario) {
		super(FabApiRestIntWhatsappMensagem.class, pTipoAgente, pUsuario);
	}
}