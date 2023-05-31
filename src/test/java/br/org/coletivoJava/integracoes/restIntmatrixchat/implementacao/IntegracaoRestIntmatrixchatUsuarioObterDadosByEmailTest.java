/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntmatrixchat.implementacao;

import br.org.coletivoJava.integracoes.matrixChat.FabApiRestIntMatrixChatUsuarios;
import com.super_bits.Super_Bits.mktMauticIntegracao.configAppp.ConfiguradorCoreMatrixChatIntegracao;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author salvio
 */
public class IntegracaoRestIntmatrixchatUsuarioObterDadosByEmailTest {

    public IntegracaoRestIntmatrixchatUsuarioObterDadosByEmailTest() {
    }

    @Test
    public void testSomeMethod() {
        SBCore.configurar(new ConfiguradorCoreMatrixChatIntegracao(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        GestaoTokenRestIntmatrixchat gtoke = (GestaoTokenRestIntmatrixchat) FabApiRestIntMatrixChatUsuarios.USUARIO_OBTER_DADOS.getGestaoToken();
        if (!gtoke.isTemTokemAtivo()) {
            gtoke.gerarNovoToken();
        }
        ItfRespostaWebServiceSimples resposta = FabApiRestIntMatrixChatUsuarios.USUARIO_OBTER_DADOS_BY_EMAIL.getAcao("renata.mota@casanovadigital.com.br").getResposta();
        System.out.println(resposta.getRespostaTexto());
        String userId = resposta.getRespostaComoObjetoJson().getString("user_id");

        ItfRespostaWebServiceSimples respostaUser = FabApiRestIntMatrixChatUsuarios.USUARIO_OBTER_DADOS.getAcao(userId).getResposta();
        System.out.println(respostaUser.getRespostaTexto());

        resposta.dispararMensagens();

        Assert.assertTrue("Falha criando usuário" + resposta.getRespostaTexto(), resposta.isSucesso());

    }

}
