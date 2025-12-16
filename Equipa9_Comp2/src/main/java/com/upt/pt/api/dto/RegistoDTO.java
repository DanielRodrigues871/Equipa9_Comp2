package com.upt.pt.api.dto;

public class RegistoDTO {

    private String nome;
    private String email;
    private String password;
    private String tipo;          // ESTUDANTE, COORDENADOR, REPRESENTANTE

    // ESTUDANTE
    private String cursoId;
    private String numeroEstudante;
    private Integer anoMatricula;

    // COORDENADOR
    private String departamentoId;

    // REPRESENTANTE
    private String empresaId;
    private String cargo;
    private String telefone;

    public RegistoDTO() {
    }

    // ================= GETTERS =================

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getTipo() { return tipo; }
    public String getCursoId() { return cursoId; }
    public String getNumeroEstudante() { return numeroEstudante; }
    public Integer getAnoMatricula() { return anoMatricula; }
    public String getDepartamentoId() { return departamentoId; }
    public String getEmpresaId() { return empresaId; }
    public String getCargo() { return cargo; }
    public String getTelefone() { return telefone; }


    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
    public void setNumeroEstudante(String numeroEstudante) { this.numeroEstudante = numeroEstudante; }
    public void setAnoMatricula(Integer anoMatricula) { this.anoMatricula = anoMatricula; }
    public void setDepartamentoId(String departamentoId) { this.departamentoId = departamentoId; }
    public void setEmpresaId(String empresaId) { this.empresaId = empresaId; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}