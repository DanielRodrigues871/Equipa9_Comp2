package com.upt.pt.api.dto;

public class LoginResponseDTO {

    private String idUtilizador;
    private String nome;
    private String email;
    private String tipo; // ESTUDANTE / COORDENADOR / REPRESENTANTE

    public LoginResponseDTO() {}

    public LoginResponseDTO(String idUtilizador, String nome, String email, String tipo) {
        this.idUtilizador = idUtilizador;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public String getIdUtilizador() { return idUtilizador; }
    public void setIdUtilizador(String idUtilizador) { this.idUtilizador = idUtilizador; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
