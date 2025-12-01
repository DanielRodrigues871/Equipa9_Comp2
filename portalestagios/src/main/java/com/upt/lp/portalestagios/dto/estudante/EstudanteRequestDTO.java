package com.upt.lp.portalestagios.dto.estudante;

public class EstudanteRequestDTO {

    private String nome;
    private String email;
    private String password;

    private String numeroEstudante;
    private Integer anoMatricula;

    private String cursoId;

    public EstudanteRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNumeroEstudante() { return numeroEstudante; }
    public void setNumeroEstudante(String numeroEstudante) { this.numeroEstudante = numeroEstudante; }

    public Integer getAnoMatricula() { return anoMatricula; }
    public void setAnoMatricula(Integer anoMatricula) { this.anoMatricula = anoMatricula; }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
}

