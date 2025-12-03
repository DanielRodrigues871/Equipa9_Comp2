package com.upt.lp.componente2.dto;

import com.upt.lp.componente2.enums.StatusCandidatura;

import java.time.LocalDateTime;

public class CandidaturaDTO {

    private String id;

    private String estudanteId;
    private String estudanteNome;

    private String ofertaId;
    private String ofertaTitulo;

    private String coordenadorResponsavelId;
    private String coordenadorResponsavelNome;

    private StatusCandidatura status;
    private String cartaMotivacao;

    private LocalDateTime dataSubmissao;
    private LocalDateTime dataAnalise;
    private String observacoes;

    public CandidaturaDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstudanteId() {
        return estudanteId;
    }

    public void setEstudanteId(String estudanteId) {
        this.estudanteId = estudanteId;
    }

    public String getEstudanteNome() {
        return estudanteNome;
    }

    public void setEstudanteNome(String estudanteNome) {
        this.estudanteNome = estudanteNome;
    }

    public String getOfertaId() {
        return ofertaId;
    }

    public void setOfertaId(String ofertaId) {
        this.ofertaId = ofertaId;
    }

    public String getOfertaTitulo() {
        return ofertaTitulo;
    }

    public void setOfertaTitulo(String ofertaTitulo) {
        this.ofertaTitulo = ofertaTitulo;
    }

    public String getCoordenadorResponsavelId() {
        return coordenadorResponsavelId;
    }

    public void setCoordenadorResponsavelId(String coordenadorResponsavelId) {
        this.coordenadorResponsavelId = coordenadorResponsavelId;
    }

    public String getCoordenadorResponsavelNome() {
        return coordenadorResponsavelNome;
    }

    public void setCoordenadorResponsavelNome(String coordenadorResponsavelNome) {
        this.coordenadorResponsavelNome = coordenadorResponsavelNome;
    }

    public StatusCandidatura getStatus() {
        return status;
    }

    public void setStatus(StatusCandidatura status) {
        this.status = status;
    }

    public String getCartaMotivacao() {
        return cartaMotivacao;
    }

    public void setCartaMotivacao(String cartaMotivacao) {
        this.cartaMotivacao = cartaMotivacao;
    }

    public LocalDateTime getDataSubmissao() {
        return dataSubmissao;
    }

    public void setDataSubmissao(LocalDateTime dataSubmissao) {
        this.dataSubmissao = dataSubmissao;
    }

    public LocalDateTime getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(LocalDateTime dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}