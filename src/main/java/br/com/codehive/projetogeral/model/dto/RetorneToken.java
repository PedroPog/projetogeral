package br.com.codehive.projetogeral.model.dto;

public class RetorneToken {

    private String Resposta;
    private boolean validacao;

    public String getResposta() {
        return Resposta;
    }

    public void setResposta(String resposta) {
        Resposta = resposta;
    }

    public boolean isValidacao() {
        return validacao;
    }

    public void setValidacao(boolean validacao) {
        this.validacao = validacao;
    }
}
