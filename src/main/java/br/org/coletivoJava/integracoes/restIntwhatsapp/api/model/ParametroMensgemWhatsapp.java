/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.api.model;

/**
 *
 * @author salvio
 */
public class ParametroMensgemWhatsapp implements ItfParametroMensagemWhatsapp {

    private int codigoParametro;
    private String valor;
    private String nome;
    private String tipoParametroWtza;
    private boolean cabecalho;

    public ParametroMensgemWhatsapp(int codigoParametro, String valor, String nome, String tipoParametroWtza, boolean cabecalho) {
        this.codigoParametro = codigoParametro;
        this.valor = valor;
        this.nome = nome;
        this.tipoParametroWtza = tipoParametroWtza;
        this.cabecalho = cabecalho;
    }

    public ParametroMensgemWhatsapp(int codigoParametro, String valor, boolean cabecalho) {
        this.codigoParametro = codigoParametro;
        this.valor = valor;

        this.tipoParametroWtza = "text";
        this.cabecalho = cabecalho;
    }

    @Override
    public String getValor() {
        return valor;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getCodigoParametro() {
        return codigoParametro;
    }

    @Override
    public String getTipoParametroWtzap() {
        return tipoParametroWtza;
    }

    @Override
    public boolean isCabecalho() {
        return cabecalho;
    }

}
