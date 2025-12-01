package com.upt.lp.componente2.dto;

import java.time.LocalDateTime;

public class RepresentanteEmpresaDTO {
    private String id;
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private String cargo;
    private String empresaId;
    private String empresaNome;
    private String telefone;

    public RepresentanteEmpresaDTO() {
    }

    public RepresentanteEmpresaDTO(String id, String nome, String email, LocalDateTime dataCriacao, 
                                  LocalDateTime dataAtualizacao, String cargo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
        this.cargo = cargo;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getEmpresaId() { return empresaId; }
    public void setEmpresaId(String empresaId) { this.empresaId = empresaId; }

    public String getEmpresaNome() { return empresaNome; }
    public void setEmpresaNome(String empresaNome) { this.empresaNome = empresaNome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
