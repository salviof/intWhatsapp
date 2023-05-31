/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntmatrixchat.implementacao;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;
import jakarta.json.JsonObject;

/**
 *
 * @author salvio
 */
public class UtilMatrixApiServer {

    public static RespostaWebServiceSimples gerarRespostaWSTratamentoFino(RespostaWebServiceSimples pRespostaWSSemTratamento) {
        if (pRespostaWSSemTratamento == null) {
            return pRespostaWSSemTratamento;
        }
        if (UtilSBCoreStringValidador.isNuloOuEmbranco(pRespostaWSSemTratamento.getResposta())) {
            return pRespostaWSSemTratamento;
        }
        JsonObject json = UtilSBCoreJson.getJsonObjectByTexto(pRespostaWSSemTratamento.getResposta());
        if (json.containsKey("error")) {
            String cofigoErro = json.getString("errcode");
            pRespostaWSSemTratamento.addErro("Erro código" + cofigoErro + ", " + json.getString("error"));
        }
        return pRespostaWSSemTratamento;
    }
}
