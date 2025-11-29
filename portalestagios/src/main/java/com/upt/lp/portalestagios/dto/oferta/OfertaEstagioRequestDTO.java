package com.upt.lp.portalestagios.dto.oferta;

import java.time.LocalDate;
import java.util.UUID;

public class OfertaEstagioRequestDTO {

    private String titulo;
    private String descricao;

    private UUID empresaId;
    private UUID coordenadorId;
    private UUID cursoId;
    private UUID areaId;

    private String tipo; 
    private int numeroVagas;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    private String localizacao;
    private String requisitos;

    public OfertaEstagioRequestDTO() {}

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

    public UUID getEmpresaId() {
        return empresaId;
    }
    public void setEmpresaId(UUID empresaId) {
        this.empresaId = empresaId;
    }

    public UUID getCoordenadorId() {
        return coordenadorId;
    }
    public void setCoordenadorId(UUID coordenadorId) {
        this.coordenadorId = coordenadorId;
    }

    public UUID getCursoId() {
        return cursoId;
    }
    public void setCursoId(UUID cursoId) {
        this.cursoId = cursoId;
    }

    public UUID getAreaId() {
        return areaId;
    }
    public void setAreaId(UUID areaId) {
        this.areaId = areaId;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumeroVagas() {
        return numeroVagas;
    }
    public void setNumeroVagas(int numeroVagas) {
        this.numeroVagas = numeroVagas;
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
