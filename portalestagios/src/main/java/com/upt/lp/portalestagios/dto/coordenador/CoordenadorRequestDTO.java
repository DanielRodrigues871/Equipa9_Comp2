package com.upt.lp.portalestagios.dto.coordenador;

import java.util.UUID;

public class CoordenadorRequestDTO {
    private String nome;
    private String email;
    private String password;
    private UUID departamentoId; // associação por UUID

    public CoordenadorRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UUID getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(UUID departamentoId) { this.departamentoId = departamentoId; }
}

