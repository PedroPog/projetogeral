package br.com.codehive.projetogeral.model.dto;

public class RetornGeneric {

    private String descricao;
    private boolean status;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
