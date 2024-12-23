package br.com.codehive.projetogeral.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_projetos")
public class Projetos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long projetoId;

    @Column(name = "name_projeto",nullable = false)
    private String nameProjeto;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "capa_projeto")
    private String capaProjeto = "https://placehold.co/400x600";

    private String url;

    @Column(name = "linguagem_programcao",nullable = false)
    private String linguagemProgramacao;

    @Column(name = "website")
    private String website;

    @Column(name = "status_pg")
    private boolean status = false;

    public Projetos() {
    }

    public Projetos(Long projetoId, String nameProjeto, String descricao, String capaProjeto, String url, String linguagemProgramacao, String website, boolean status) {
        this.projetoId = projetoId;
        this.nameProjeto = nameProjeto;
        this.descricao = descricao;
        this.capaProjeto = capaProjeto;
        this.url = url;
        this.linguagemProgramacao = linguagemProgramacao;
        this.website = website;
        this.status = status;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
    }

    public String getNameProjeto() {
        return nameProjeto;
    }

    public void setNameProjeto(String nameProjeto) {
        this.nameProjeto = nameProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCapaProjeto() {
        return capaProjeto;
    }

    public void setCapaProjeto(String capaProjeto) {
        this.capaProjeto = capaProjeto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinguagemProgramacao() {
        return linguagemProgramacao;
    }

    public void setLinguagemProgramacao(String linguagemProgramacao) {
        this.linguagemProgramacao = linguagemProgramacao;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
