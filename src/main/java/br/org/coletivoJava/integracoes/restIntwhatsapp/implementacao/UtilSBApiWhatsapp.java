/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import br.org.coletivoJava.integracoes.whatsapp.FabApiRestIntWhatsappMedia;
import br.org.coletivoJava.integracoes.whatsapp.config.FabConfigApiWhatsapp;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UTilSBCoreInputs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import jakarta.json.JsonObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author salvio
 */
public class UtilSBApiWhatsapp {

    public static void gerarTratmentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        if (pRespostaWSSemTratamento == null) {
            return;
        }
        System.out.println("executando tratamento fino em:");
        System.out.println(pRespostaWSSemTratamento.getRespostaTexto());

        try {
            JsonObject resposta = pRespostaWSSemTratamento.getRespostaComoObjetoJson();
            System.out.println(UtilSBCoreJson.getTextoByJsonObjeect(resposta));

            if (!pRespostaWSSemTratamento.isSucesso()) {

                if (resposta.containsKey("error")) {
                    JsonObject erroJson = resposta.getJsonObject("error");
                    String mensagem = erroJson.getString("message");
                    String tipo = erroJson.getString("type");
                    int codig = erroJson.getInt("code");
                    int subcodig = 0;
                    if (erroJson.containsKey("error_subcode")) {
                        subcodig = erroJson.getInt("error_subcode");
                    }
                    String codigoRastreamento = erroJson.getString("fbtrace_id");
                    StringBuilder msg = new StringBuilder();
                    msg.append("Erro, tipo").append(tipo).append(" Cod-Subcodigo: ").append(codig).append("-").append(subcodig)
                            .append(" ").append(mensagem)
                            .append(" Track: ")
                            .append(codigoRastreamento);
                    pRespostaWSSemTratamento.addErro(msg.toString());

                }
            }
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "falha interpretando json de resposta", t);
        }

    }

    public static InputStream getMediaFromMessage(String pCodigo) {

        try {
            ItfAcaoApiRest acaoDadosMedia = FabApiRestIntWhatsappMedia.MEDIA_GET_URL.getAcao(pCodigo);
            ItfRespostaWebServiceSimples resp = acaoDadosMedia.getResposta();
            String url = resp.getRespostaComoObjetoJson().getString("url");
            //String tipoArquivo = resp.getRespostaComoObjetoJson().getString("mime_type");
            Map<String, String> header = new IntegracaoRestIntwhatsapp_HeaderPadrao(acaoDadosMedia).getHeaderPadrao();
            System.out.println("Obtendo arquivo pela url " + url);
            return UTilSBCoreInputs.getStreamBuffredByURL(url, -1, -1, header);
        } catch (Throwable t) {
            System.out.println("Falha obtendo arquivo" + t.getMessage());
            return null;
        }

    }

    public static String mediaUpload(byte[] arquivo, String pNomeArquivo, String pTipoArquivo) throws Exception {
        // URL da requisição (substitua <PHONE_NUMBER_ID> com o ID correto)
        ConfigModulo config = SBCore.getConfigModulo(FabConfigApiWhatsapp.class);
        String urlString = "https://graph.facebook.com/v21.0/" + config.getPropriedade(FabConfigApiWhatsapp.CODIGO_USUARIO) + "/media";
        URL url = new URL(urlString);

        // Estabelece a conexão
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true); // Permite o envio de dados no corpo da requisição
        String token = FabApiRestIntWhatsappMedia.MEDIA_POST_NOVA_MEDIA.getGestaoToken().getToken();
        // Cabeçalho para autenticação
        connection.setRequestProperty("Authorization", "Bearer " + token);

        // Define os parâmetros do formulário
        String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        // Escreve os parâmetros no corpo da requisição
        try (DataOutputStream out = new DataOutputStream(connection.getOutputStream())) {
            // Especifica o arquivo de áudio a ser enviado
            out.writeBytes("--" + boundary + "\r\n");
            out.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + pNomeArquivo + "\"\r\n");
            out.writeBytes("Content-Type: " + pTipoArquivo + "\r\n");
            out.writeBytes("\r\n");

            // Lê o arquivo de áudio e envia
            out.write(arquivo, 0, arquivo.length);

            out.writeBytes("\r\n");

            // Parâmetro 'type' para o tipo do arquivo (aqui "audio/ogg")
            out.writeBytes("--" + boundary + "\r\n");
            out.writeBytes("Content-Disposition: form-data; name=\"type\"\r\n");
            out.writeBytes("\r\n");
            out.writeBytes("audio/ogg\r\n");

            // Parâmetro 'messaging_product' para especificar o WhatsApp
            out.writeBytes("--" + boundary + "\r\n");
            out.writeBytes("Content-Disposition: form-data; name=\"messaging_product\"\r\n");
            out.writeBytes("\r\n");
            out.writeBytes("whatsapp\r\n");

            out.writeBytes("--" + boundary + "--\r\n");
            out.flush();
        }

        // Lê a resposta da requisição
        int responseCode = connection.getResponseCode();
        System.out.println("Resposta do servidor: " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JsonObject jsonResp = UtilSBCoreJson.getJsonObjectByTexto(response.toString());
        if (jsonResp != null) {
            if (jsonResp.containsKey("id")) {
                return jsonResp.getString("id");
            }
        }
        return null;
    }

}
