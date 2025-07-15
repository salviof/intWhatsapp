/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import org.junit.Test;

/**
 * @author salvio
 */
public class UtilSBApiWhatsappTest {

    public UtilSBApiWhatsappTest() {
    }

    private final static String RESPOSTA_PASSOU_24_HORAS
            = "{\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"114354588403482\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"553121159755\",\"phone_number_id\":\"103007756220088\"},\"statuses\":[{\"id\":\"wamid.HBgMNTUzMTg0MTc4NTUwFQIAERgSNDY3N0JCQzJGMkZBMDhFNUYxAA==\",\"status\":\"failed\",\"timestamp\":\"1694459327\",\"recipient_id\":\"553184178550\",\"errors\":[{\"code\":131047,\"title\":\"Re-engagement message\",\"message\":\"Re-engagement message\",\"error_data\":{\"details\":\"Message failed to send because more than 24 hours have passed since the customer last replied to this number.\"},\"href\":\"https:\\/\\/developers.facebook.com\\/docs\\/whatsapp\\/cloud-api\\/support\\/error-codes\\/\"}]}]},\"field\":\"messages\"}]}]}";

    /**
     * Test of gerarTratmentoFino method, of class UtilSBApiWhatsapp.
     */
    @Test
    public void testGerarTratmentoFino() {
        RespostaWebServiceSimples resposta = new RespostaWebServiceSimples(200, "Erro código x", RESPOSTA_PASSOU_24_HORAS);
        UtilSBApiWhatsapp.gerarTratamentoFino(resposta);
        // TODO review the generated test code and remove the default call to fail.

    }

}
