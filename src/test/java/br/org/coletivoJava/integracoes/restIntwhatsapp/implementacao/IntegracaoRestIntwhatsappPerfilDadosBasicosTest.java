/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappPerfil;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.Super_Bits.whatsapp.configAppp.ConfiguradorCoreIntWhatsappIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreOutputs;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import java.io.BufferedInputStream;
import java.util.Map;
import org.junit.Test;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntwhatsappPerfilDadosBasicosTest {

    public IntegracaoRestIntwhatsappPerfilDadosBasicosTest() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        SBCore.configurar(new ConfiguradorCoreIntWhatsappIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        String tok = SBCore.getConfigModulo(FabConfigApiWhatsapp.class).getPropriedade(FabConfigApiWhatsapp.TOKEN_ACESSO);

        GestaoTokenRestIntwhatsapp gestaoTK = (GestaoTokenRestIntwhatsapp) FabApiRestIntWhatsappPerfil.PERFIL_DADOS_BASICOS.getGestaoToken();

        ItfRespostaWebServiceSimples resp = FabApiRestIntWhatsappPerfil.PERFIL_DADOS_BASICOS.getAcao("3184178550").getResposta();
        String profileURL = resp.getRespostaComoObjetoJson().getJsonArray("data").get(0).asJsonObject().getString("profile_picture_url");
        Map<String, String> header = new IntegracaoRestIntwhatsapp_HeaderPadrao(FabApiRestIntWhatsappPerfil.PERFIL_DADOS_BASICOS.getAcao()).getHeaderPadrao();

        System.out.println("Obtendo arquivo pela url " + profileURL);
        BufferedInputStream input
                = UTilSBCoreInputs.getStreamBuffredByURL(profileURL, -1, -1, header);
        UtilSBCoreOutputs.salvarArquivoBfInput(input, "/home/salvio/Imagens/avatarSalvioAPiWhatsap.png");
        System.out.println(resp.getRespostaTexto());

    }

}
