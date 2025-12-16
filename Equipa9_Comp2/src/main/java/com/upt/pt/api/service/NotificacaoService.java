package com.upt.pt.api.service;

import com.upt.pt.api.entity.Notificacao;
import com.upt.pt.api.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public Notificacao enviar(String utilizadorId, String titulo, String mensagem) {
        Notificacao n = new Notificacao();
        n.setUtilizadorId(utilizadorId);
        n.setTitulo(titulo);
        n.setMensagem(mensagem);
        return notificacaoRepository.save(n);
    }

    public List<Notificacao> listarPorUtilizador(String utilizadorId) {
        return notificacaoRepository
                .findByUtilizadorIdOrderByDataCriacaoDesc(utilizadorId);
    }

    public void marcarComoLida(String id) {
        Notificacao n = notificacaoRepository.findById(id)
                .orElseThrow();
        n.setLida(true);
        notificacaoRepository.save(n);
    }

    public void marcarTodasComoLidas(String utilizadorId) {
        List<Notificacao> lista =
                notificacaoRepository.findByUtilizadorIdOrderByDataCriacaoDesc(utilizadorId);

        for (Notificacao n : lista) {
            n.setLida(true);
        }

        notificacaoRepository.saveAll(lista);
    }
}