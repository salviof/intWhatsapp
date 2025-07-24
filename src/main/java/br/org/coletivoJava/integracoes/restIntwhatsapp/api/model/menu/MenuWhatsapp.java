/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.mensagem.MensagemSimplesEnvioWhatsapp;

import java.util.List;

/**
 * @author salvio
 */
public class MenuWhatsapp {

    private MensagemSimplesEnvioWhatsapp mensagem;

    private List<ItemMenuWhatsapp> itensMenu;

    public MensagemSimplesEnvioWhatsapp getMensagem() {
        return mensagem;
    }

    public void setMensagem(MensagemSimplesEnvioWhatsapp mensagem) {
        this.mensagem = mensagem;
    }

    public List<ItemMenuWhatsapp> getItensMenu() {
        return itensMenu;
    }

    public void setItensMenu(List<ItemMenuWhatsapp> itensMenu) {
        this.itensMenu = itensMenu;
    }

    public boolean isCompativelComMenuSimples() {
//        return itensMenu != null && itensMenu.size() <= 3;
        if (itensMenu == null || itensMenu.size() > 3) {
            return false;
        }
        return itensMenu.stream()
                .allMatch(item -> item.getDescricao() == null || item.getDescricao().trim().isEmpty());
    }
}
