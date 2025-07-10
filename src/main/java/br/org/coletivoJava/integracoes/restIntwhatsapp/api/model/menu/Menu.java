/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.menu;

import br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.mensagem.MensagemSimplesEnvioWhatsapp;
import java.util.List;
import org.coletivojava.fw.api.objetoNativo.mensagem.Mensagem;

/**
 *
 * @author salvio
 */
public class Menu {

    private MensagemSimplesEnvioWhatsapp mensagem;

    private List<ItemMenu> itensMenu;

    public MensagemSimplesEnvioWhatsapp getMensagem() {
        return mensagem;
    }

    public void setMensagem(MensagemSimplesEnvioWhatsapp mensagem) {
        this.mensagem = mensagem;
    }

    public List<ItemMenu> getItensMenu() {
        return itensMenu;
    }

    public void setItensMenu(List<ItemMenu> itensMenu) {
        this.itensMenu = itensMenu;
    }

}
