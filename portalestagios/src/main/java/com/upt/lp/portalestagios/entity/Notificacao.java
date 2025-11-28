package com.upt.lp.portalestagios.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Utilizador destinatario;

    @Column(name = "mensagem", length = 2000, nullable = false)
    private String mensagem;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "lida", nullable = false)
    private boolean lida = false;

    public Notificacao() {}
    public Notificacao(Utilizador destinatario, String mensagem) {
        this.destinatario = destinatario;
        this.mensagem = mensagem;
    }

    public UUID getId() { return id; }
    public Utilizador getDestinatario() { return destinatario; }
    public void setDestinatario(Utilizador destinatario) { this.destinatario = destinatario; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public boolean isLida() { return lida; }
    public void setLida(boolean lida) { this.lida = lida; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notificacao)) return false;
        Notificacao that = (Notificacao) o;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
