package com.upt.pt.api.service;

import com.upt.pt.api.entity.Notificacao;
import com.upt.pt.api.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável por gerir notificações dos utilizadores.
 */
@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public Notificacao enviar(String utilizadorId, String titulo, String mensagem) {

        System.out.println("ENVIANDO NOTIFICAÇÃO PARA UTILIZADOR " + utilizadorId);
        System.out.println("TITULO: " + titulo);
        System.out.println("MENSAGEM: " + mensagem);

        Notificacao n = new Notificacao();
        n.setUtilizadorId(utilizadorId);
        n.setTitulo(titulo);
        n.setMensagem(mensagem);

        return notificacaoRepository.save(n);
    }


    public List<Notificacao> listarPorUtilizador(String utilizadorId) {
        return notificacaoRepository.findByUtilizadorIdOrderByDataCriacaoDesc(utilizadorId);
    }

    public void marcarComoLida(String id) {
        Notificacao n = notificacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notificação não encontrada."));
        n.setLida(true);
        notificacaoRepository.save(n);
    }
}