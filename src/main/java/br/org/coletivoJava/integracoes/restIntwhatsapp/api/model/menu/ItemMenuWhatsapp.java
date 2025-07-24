/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu;

/**
 * @author salvio
 */
public class ItemMenuWhatsapp {

    private String id;
    private String titulo;
    private String descricao;
    private ItemMenuWhatsapp origem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ItemMenuWhatsapp getOrigem() {
        return origem;
    }

    public void setOrigem(ItemMenuWhatsapp origem) {
        this.origem = origem;
    }

}
