package br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.templates;

import java.util.List;

public class MensagemTemplate {

    private String nome;
    private List<ComponenteTemplate> componentes;

    public List<ComponenteTemplate> getComponentes() {
        return componentes;
    }

    public void setComponentes(List<ComponenteTemplate> componentes) {
        this.componentes = componentes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
