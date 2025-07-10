package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMensagem;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMensagem;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntwhatsappMensagem(tipo = FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ATE_3_OPCOES_ENVIAR)
public class IntegracaoRestIntwhatsappMensagemMenuAte3OpcoesEnviar
		extends
			AcaoApiIntegracaoAbstrato {

	public IntegracaoRestIntwhatsappMensagemMenuAte3OpcoesEnviar(
			final FabTipoAgenteClienteApi pTipoAgente,
			final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
		super(FabApiRestIntWhatsappMensagem.MENSAGEM_MENU_ATE_3_OPCOES_ENVIAR,
				pTipoAgente, pUsuario, pParametro);
	}
}