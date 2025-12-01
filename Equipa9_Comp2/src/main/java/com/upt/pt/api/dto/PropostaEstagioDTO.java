package com.upt.pt.api.dto;

import java.time.LocalDate;
import java.util.List;

public class PropostaEstagioDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String requisitos;
    private String beneficios;
    private String localizacao;
    private int duracaoMeses;
    private boolean remunerado;
    private double valorRemuneracao;
    private int vagasDisponiveis;
    private String tipo;
    private String status;
    private LocalDate dataProposta;

    private Long empresaId;
    private String empresaNome;

    private String representanteId;
    private String representanteNome;

    private List<String> areasIds;
    private List<String> areasNomes;

    public PropostaEstagioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
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

    public boolean isRemunerado() {
        return remunerado;
    }

    public void setRemunerado(boolean remunerado) {
        this.remunerado = remunerado;
    }

    public double getValorRemuneracao() {
        return valorRemuneracao;
    }

    public void setValorRemuneracao(double valorRemuneracao) {
        this.valorRemuneracao = valorRemuneracao;
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(int vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(LocalDate dataProposta) {
        this.dataProposta = dataProposta;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaNome() {
        return empresaNome;
    }

    public void setEmpresaNome(String empresaNome) {
        this.empresaNome = empresaNome;
    }

    public String getRepresentanteId() {
        return representanteId;
    }

    public void setRepresentanteId(String representanteId) {
        this.representanteId = representanteId;
    }

    public String getRepresentanteNome() {
        return representanteNome;
    }

    public void setRepresentanteNome(String representanteNome) {
        this.representanteNome = representanteNome;
    }

    public List<String> getAreasIds() {
        return areasIds;
    }

    public void setAreasIds(List<String> areasIds) {
        this.areasIds = areasIds;
    }

    public List<String> getAreasNomes() {
        return areasNomes;
    }

    public void setAreasNomes(List<String> areasNomes) {
        this.areasNomes = areasNomes;
    }
}
