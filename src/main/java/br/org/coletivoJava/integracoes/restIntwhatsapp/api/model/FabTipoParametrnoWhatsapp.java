/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.api.model;

/**
 *
 * @author salvio
 */
public enum FabTipoParametrnoWhatsapp {

    TEXT,
    DOCUMENT,
    IMAGE,
    VIDEO,
    LOCATION,
    RODAPE_BOTAO;

    public static FabTipoParametrnoWhatsapp getTipoByString(String pSTring) {
        String var = pSTring.toLowerCase();
        switch (var) {

            case "text":
                return FabTipoParametrnoWhatsapp.TEXT;
            case "document":
                return FabTipoParametrnoWhatsapp.DOCUMENT;
            case "image":
                return FabTipoParametrnoWhatsapp.IMAGE;
            case "video":
                return FabTipoParametrnoWhatsapp.VIDEO;
            case "location":
                return FabTipoParametrnoWhatsapp.LOCATION;
            case "button":
                return FabTipoParametrnoWhatsapp.RODAPE_BOTAO;
            default:
                throw new AssertionError(pSTring + "não é um tipo de parâmetro conhecido pelo whatsapp: "
                        + "utilize: Document,Image,Video ou Location"
                );
        }
    }

}
