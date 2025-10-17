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

    public MensagemSimplesEnvioWhatsapp setCabecalho(String pCabecalho) {
        this.cabecalho = pCabecalho;
        return this;
    }

    public String getCorpo() {
        return corpo;
    }

    public MensagemSimplesEnvioWhatsapp setCorpo(String corpo) {
        this.corpo = corpo;
        return this;
    }

    public String getRodape() {
        return rodape;
    }

    public MensagemSimplesEnvioWhatsapp setRodape(String rodape) {
        this.rodape = rodape;
        return this;
    }

    public boolean isTemCabecalho() {
        return cabecalho != null && !cabecalho.isEmpty();
    }

    public boolean isTemRodape() {
        return rodape != null && !rodape.isEmpty();
    }

}
