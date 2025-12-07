package com.upt.pt.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Notificação simples associada ao ID de um utilizador.
 */
@Entity
@Table(name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String titulo;
    private String mensagem;

    @Column(nullable = false)
    private boolean lida;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    /**
     * Guarda o ID do utilizador, porque Utilizador é @MappedSuperclass.
     */
    @Column(name = "utilizador_id", nullable = false, length = 36)
    private String utilizadorId;

    public Notificacao() {
        this.dataCriacao = LocalDateTime.now();
        this.lida = false;
    }

    // Getters e Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public boolean isLida() { return lida; }
    public void setLida(boolean lida) { this.lida = lida; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getUtilizadorId() { return utilizadorId; }
    public void setUtilizadorId(String utilizadorId) { this.utilizadorId = utilizadorId; }
    
}