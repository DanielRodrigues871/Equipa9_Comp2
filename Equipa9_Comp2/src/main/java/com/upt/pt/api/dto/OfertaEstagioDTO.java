package com.upt.pt.api.dto;

import com.upt.pt.api.enums.StatusOferta;
import com.upt.pt.api.enums.TipoEstagio;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OfertaEstagioDTO {

    private String id;

    private String titulo;
    private String descricao;

    private String empresaId;
    private String empresaNome;

    private String areaId;
    private String areaNome;

    private String cursoId;
    private String cursoNome;

    private String coordenadorResponsavelId;
    private String coordenadorResponsavelNome;

    private TipoEstagio tipo;
    private String localizacao;
    private int duracaoMeses;
    private String requisitos;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataLimiteInscricao;

    private StatusOferta status;
    private int numeroVagas;

    private LocalDateTime dataPublicacao;
    private LocalDateTime dataAprovacao;

    private int numeroCandidaturas;

    public OfertaEstagioDTO() {
    }

    // getters e setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaNome() {
        return areaNome;
    }

    public void setAreaNome(String areaNome) {
        this.areaNome = areaNome;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
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

    public TipoEstagio getTipo() {
        return tipo;
    }

    public void setTipo(TipoEstagio tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDate getDataLimiteInscricao() {
        return dataLimiteInscricao;
    }

    public void setDataLimiteInscricao(LocalDate dataLimiteInscricao) {
        this.dataLimiteInscricao = dataLimiteInscricao;
    }

    public StatusOferta getStatus() {
        return status;
    }

    public void setStatus(StatusOferta status) {
        this.status = status;
    }

    public int getNumeroVagas() {
        return numeroVagas;
    }

    public void setNumeroVagas(int numeroVagas) {
        this.numeroVagas = numeroVagas;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public LocalDateTime getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(LocalDateTime dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public int getNumeroCandidaturas() {
        return numeroCandidaturas;
    }

    public void setNumeroCandidaturas(int numeroCandidaturas) {
        this.numeroCandidaturas = numeroCandidaturas;
    }
}
