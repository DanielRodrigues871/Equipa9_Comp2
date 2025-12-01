package com.upt.lp.portalestagios.dto.auth;

public class RegisterRequestDTO {

    /**
     * Campos comuns
     */
    private String nome;
    private String email;
    private String password;

    /**
     * Tipo de utilizador a criar: "ESTUDANTE", "COORDENADOR", "REPRESENTANTE"
     * (usar exatamente estas strings ou podes criar um enum)
     */
    private String role;

    // ----------------- Estudante -----------------
    private String numeroEstudante;
    private Integer anoMatricula;
    private String cursoId;

    // ----------------- Coordenador -----------------
    private String departamentoId;

    // ----------------- Representante / Empresa -----------------
    private String cargo;
    private String empresaId;       // se já existir empresa na BD -> associa
    private String empresaNome;     // se empresaId == null -> cria nova empresa com estes campos
    private String empresaNif;
    private String empresaEmail;
    private String empresaMorada;

    public RegisterRequestDTO() {}

    // getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getNumeroEstudante() { return numeroEstudante; }
    public void setNumeroEstudante(String numeroEstudante) { this.numeroEstudante = numeroEstudante; }

    public Integer getAnoMatricula() { return anoMatricula; }
    public void setAnoMatricula(Integer anoMatricula) { this.anoMatricula = anoMatricula; }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }

    public String getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(String departamentoId) { this.departamentoId = departamentoId; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getEmpresaId() { return empresaId; }
    public void setEmpresaId(String empresaId) { this.empresaId = empresaId; }

    public String getEmpresaNome() { return empresaNome; }
    public void setEmpresaNome(String empresaNome) { this.empresaNome = empresaNome; }

    public String getEmpresaNif() { return empresaNif; }
    public void setEmpresaNif(String empresaNif) { this.empresaNif = empresaNif; }

    public String getEmpresaEmail() { return empresaEmail; }
    public void setEmpresaEmail(String empresaEmail) { this.empresaEmail = empresaEmail; }

    public String getEmpresaMorada() { return empresaMorada; }
    public void setEmpresaMorada(String empresaMorada) { this.empresaMorada = empresaMorada; }
}
