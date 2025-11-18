package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMedia;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMedia;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

@InfoIntegracaoRestIntwhatsappMedia(tipo = FabApiRestIntWhatsappMedia.MEDIA_GET_URL)
public class IntegracaoRestIntwhatsappMediaGetUrl
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMediaGetUrl(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMedia.MEDIA_GET_URL, pTipoAgente, pUsuario,
                pParametro);
    }

    @Override
    public String gerarUrlRequisicao() {
        String url = super.gerarUrlRequisicao();
        return url;
    }

}
