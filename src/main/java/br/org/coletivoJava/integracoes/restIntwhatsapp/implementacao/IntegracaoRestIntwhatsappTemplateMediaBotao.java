package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappTemplate;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappTemplate;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestIntwhatsappTemplate(tipo = FabApiRestIntWhatsappTemplate.TEMPLATE_MEDIA_BOTAO)
public class IntegracaoRestIntwhatsappTemplateMediaBotao
		extends
			AcaoApiIntegracaoAbstrato {

	public IntegracaoRestIntwhatsappTemplateMediaBotao(
			final FabTipoAgenteClienteApi pTipoAgente,
			final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
		super(FabApiRestIntWhatsappTemplate.TEMPLATE_MEDIA_BOTAO, pTipoAgente,
				pUsuario, pParametro);
	}
}