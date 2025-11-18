package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.InfoIntegracaoRestIntwhatsappMedia;
import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMedia;
import com.google.common.net.MediaType;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;

@InfoIntegracaoRestIntwhatsappMedia(tipo = FabApiRestIntWhatsappMedia.MEDIA_POST_NOVA_MEDIA)
public class IntegracaoRestIntwhatsappMediaPostNovaMedia
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestIntwhatsappMediaPostNovaMedia(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestIntWhatsappMedia.MEDIA_POST_NOVA_MEDIA, pTipoAgente,
                pUsuario, pParametro);
    }


    @Override
    public String gerarCorpoRequisicao() {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        byte[] fileContent;
        try {
            fileContent = FileUtils.readFileToByteArray(new File("/home/superBits/projetos/coletivoJava/source/integracao/intWhatsapp/src/main/resources/arquivos/audioExemplo.ogg"));
            String dadosCode64 = Base64.getEncoder().encodeToString(fileContent);
            dadosCode64 = URLEncoder.encode(dadosCode64, StandardCharsets.UTF_8.toString());

            StringBuilder corpoBuilder = new StringBuilder();
            corpoBuilder.append("Content-Disposition: form-data; name=\"file\"; filename=\"cross-trainers-summer-sale.jpg\"\r\n");

            //Part messageProduct = Part.createFormData("messaging_product", "whatsapp");
            //-F 'file=@"2jC60Vdjn/cross-trainers-summer-sale.jpg"' \
            //-F 'type="image/jpeg"' \
            //-F 'messaging_product="whatsapp"'
            //byte[] postDataBytes = dados.toString().getBytes("UTF-8");
            return null;
            // return corpoBuilder.toString();
        } catch (IOException ex) {
            Logger.getLogger(IntegracaoRestIntwhatsappMediaPostNovaMedia.class.getName()).log(Level.SEVERE, null, ex);
        }

        return super.gerarCorpoRequisicao(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected RespostaWebServiceSimples gerarRespostaTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        UtilSBApiWhatsapp.gerarTratamentoFino(pRespostaWSSemTratamento);
        return pRespostaWSSemTratamento;
    }

    @Override
    public Map<String, String> gerarCabecalho() {
        Map<String, String> cabecalho = super.gerarCabecalho(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        cabecalho.put("content-type", "application/x-www-form-urlencoded");

        return cabecalho;
    }
}
