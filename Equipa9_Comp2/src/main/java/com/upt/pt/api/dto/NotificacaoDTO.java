package com.upt.pt.api.dto;

import java.time.LocalDateTime;

/**
 * DTO que representa uma notificação enviada a um utilizador.
 */
public class NotificacaoDTO {

    /**
     * ID da notificação.
     */
    private String id;

    /**
     * Título do aviso.
     */
    private String titulo;

    /**
     * Mensagem detalhada da notificação.
     */
    private String mensagem;

    /**
     * ID do utilizador destinatário.
     */
    private String utilizadorId;

    /**
     * Nome do utilizador destinatário.
     */
    private String utilizadorNome;

    /**
     * Indicador se a notificação já foi lida.
     */
    private boolean lida;

    /**
     * Data/hora em que a notificação foi criada.
     */
    private LocalDateTime dataCriacao;


    // ===========================
    // Getters e Setters
    // ===========================

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(String utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    public String getUtilizadorNome() {
        return utilizadorNome;
    }

    public void setUtilizadorNome(String utilizadorNome) {
        this.utilizadorNome = utilizadorNome;
    }

    public boolean isLida() {
        return lida;
    }

    public void setLida(boolean lida) {
        this.lida = lida;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}