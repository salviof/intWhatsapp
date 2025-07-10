/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.mensagem;

/**
 *
 * @author salvio
 */
public class MensagemSimplesEnvioWhatsapp {

    private String cabecalho;

    private String corpo;

    private String rodape;

    public String getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(String cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getRodape() {
        return rodape;
    }

    public void setRodape(String rodape) {
        this.rodape = rodape;
    }

    public boolean isTemCabecalho() {
        return cabecalho != null && !cabecalho.isEmpty();
    }

    public boolean isTemRodape() {
        return rodape != null && !rodape.isEmpty();
    }

}
