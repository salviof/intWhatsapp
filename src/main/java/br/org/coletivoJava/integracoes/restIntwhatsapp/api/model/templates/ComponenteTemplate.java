package br.org.coletivoJava.integracoes.restIntwhatsapp.api.model.templates;

import java.util.List;

public class ComponenteTemplate {

    private String tipo;
    private List<ParametroTemplate> parametros;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<ParametroTemplate> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroTemplate> parametros) {
        this.parametros = parametros;
    }
}
