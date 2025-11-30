package com.upt.lp.portalestagios.dto.coordenador;

import java.util.UUID;

public class CoordenadorResponseDTO {
    private UUID id;
    private String nome;
    private String email;
    private UUID departamentoId;
    private String departamentoNome;

    public CoordenadorResponseDTO() {}

    public CoordenadorResponseDTO(UUID id, String nome, String email, UUID departamentoId, String departamentoNome) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.departamentoId = departamentoId;
        this.departamentoNome = departamentoNome;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UUID getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(UUID departamentoId) { this.departamentoId = departamentoId; }

    public String getDepartamentoNome() { return departamentoNome; }
    public void setDepartamentoNome(String departamentoNome) { this.departamentoNome = departamentoNome; }
}

