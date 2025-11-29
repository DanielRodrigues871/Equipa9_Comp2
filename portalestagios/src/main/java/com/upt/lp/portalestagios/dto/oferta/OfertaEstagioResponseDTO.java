package com.upt.lp.portalestagios.dto.oferta;

import com.upt.lp.portalestagios.enums.StatusOferta;
import com.upt.lp.portalestagios.enums.TipoEstagio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class OfertaEstagioResponseDTO {

    private UUID id;
    private String titulo;
    private String descricao;

    private String empresaNome;
    private String cursoNome;
    private String areaNome;
    private String coordenadorNome;

    private TipoEstagio tipo;
    private StatusOferta status;

    private int numeroVagas;

    private LocalDateTime dataPublicacao;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private String localizacao;
    private String requisitos;

    public OfertaEstagioResponseDTO() {}

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
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

    public String getEmpresaNome() {
        return empresaNome;
    }
    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public String getCursoNome() {
        return cursoNome;
    }
    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    public String getAreaNome() {
        return areaNome;
    }
    public void setAreaNome(String areaNome) {
        this.areaNome = areaNome;
    }

    public String getCoordenadorNome() {
        return coordenadorNome;
    }
    public void setCoordenadorNome(String coordenadorNome) {
        this.coordenadorNome = coordenadorNome;
    }

    public TipoEstagio getTipo() {
        return tipo;
    }
    public void setTipo(TipoEstagio tipo) {
        this.tipo = tipo;
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

    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getRequisitos() {
        return requisitos;
    }
    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }
}

