package com.upt.lp.componente2.dto;

import java.time.LocalDate;

public class EstagioDTO {
    private String id;
    private String estudanteId;
    private String estudanteNome;
    private String ofertaId;
    private String ofertaTitulo;
    private String cursoId;
    private String cursoNome;
    private String empresaId;
    private String empresaNome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String estadoFinal;
    private String notaFinal;
    private String observacoes;

    public EstagioDTO() {
    }

    public EstagioDTO(String id, String estudanteId, String estudanteNome, 
                     String ofertaId, String ofertaTitulo) {
        this.id = id;
        this.estudanteId = estudanteId;
        this.estudanteNome = estudanteNome;
        this.ofertaId = ofertaId;
        this.ofertaTitulo = ofertaTitulo;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEstudanteId() { return estudanteId; }
    public void setEstudanteId(String estudanteId) { this.estudanteId = estudanteId; }

    public String getEstudanteNome() { return estudanteNome; }
    public void setEstudanteNome(String estudanteNome) { this.estudanteNome = estudanteNome; }

    public String getOfertaId() { return ofertaId; }
    public void setOfertaId(String ofertaId) { this.ofertaId = ofertaId; }

    public String getOfertaTitulo() { return ofertaTitulo; }
    public void setOfertaTitulo(String ofertaTitulo) { this.ofertaTitulo = ofertaTitulo; }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }

    public String getCursoNome() { return cursoNome; }
    public void setCursoNome(String cursoNome) { this.cursoNome = cursoNome; }

    public String getEmpresaId() { return empresaId; }
    public void setEmpresaId(String empresaId) { this.empresaId = empresaId; }

    public String getEmpresaNome() { return empresaNome; }
    public void setEmpresaNome(String empresaNome) { this.empresaNome = empresaNome; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public String getEstadoFinal() { return estadoFinal; }
    public void setEstadoFinal(String estadoFinal) { this.estadoFinal = estadoFinal; }

    public String getNotaFinal() { return notaFinal; }
    public void setNotaFinal(String notaFinal) { this.notaFinal = notaFinal; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
