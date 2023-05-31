/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntmatrixchat;

import br.org.coletivoJava.integracoes.matrixChat.config.FabConfigApiMatrixChat;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringSlugs;

/**
 *
 * @author salvio
 */
public class UtilsbApiMatrixChat {

    private static ConfigModulo config = SBCore.getConfigModulo(FabConfigApiMatrixChat.class);

    public static String gerarSenhaPadrao(String pEmail) {
        String chaveSenhaMi = config.getPropriedade(FabConfigApiMatrixChat.SEGREDO);
        StringBuilder senhaBuilder = new StringBuilder();

        senhaBuilder.append("Senha@");
        String hash = chaveSenhaMi + pEmail;
        senhaBuilder.append(hash.hashCode());

        return senhaBuilder.toString();
    }

    public static String gerarSlugUserCompativel(String pTexto) {
        String slugnome = UtilSBCoreStringSlugs.gerarSlugCaixaAlta(pTexto).toLowerCase();
        return slugnome;
    }

    public static String gerarCodigoBySlugUser(String pUsername) {

        return "@" + pUsername + ":casanovadigital.com.br";
    }

}
