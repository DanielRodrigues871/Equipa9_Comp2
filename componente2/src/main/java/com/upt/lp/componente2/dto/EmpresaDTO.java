package com.upt.lp.componente2.dto;

import java.time.LocalDateTime;

public class EmpresaDTO {

    private String id;
    private String nome;
    private String nif;
    private String morada;
    private String telefone;
    private String email;
    private String website;
    private String descricao;
    private boolean ativa;
    private LocalDateTime dataCriacao;

    private int numeroRepresentantes;
    private int numeroOfertasSubmetidas;

    public EmpresaDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getNumeroRepresentantes() {
        return numeroRepresentantes;
    }

    public void setNumeroRepresentantes(int numeroRepresentantes) {
        this.numeroRepresentantes = numeroRepresentantes;
    }

    public int getNumeroOfertasSubmetidas() {
        return numeroOfertasSubmetidas;
    }

    public void setNumeroOfertasSubmetidas(int numeroOfertasSubmetidas) {
        this.numeroOfertasSubmetidas = numeroOfertasSubmetidas;
    }
}