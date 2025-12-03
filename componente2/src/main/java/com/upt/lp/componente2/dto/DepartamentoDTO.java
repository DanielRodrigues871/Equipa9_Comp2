package com.upt.lp.componente2.dto;

public class DepartamentoDTO {

    private String id;
    private String nome;
    private String codigo;
    private String descricao;

    private int numeroCursos;
    private int numeroCoordenadores;

    public DepartamentoDTO() {
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumeroCursos() {
        return numeroCursos;
    }

    public void setNumeroCursos(int numeroCursos) {
        this.numeroCursos = numeroCursos;
    }

    public int getNumeroCoordenadores() {
        return numeroCoordenadores;
    }

    public void setNumeroCoordenadores(int numeroCoordenadores) {
        this.numeroCoordenadores = numeroCoordenadores;
    }
}