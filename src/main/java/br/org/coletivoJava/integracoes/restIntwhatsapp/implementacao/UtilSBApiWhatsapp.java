/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.implementacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import jakarta.json.JsonObject;
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

}
