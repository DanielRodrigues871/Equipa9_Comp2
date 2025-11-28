package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Notificacao;
import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.repository.NotificacaoRepository;
import com.upt.lp.portalestagios.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
public class NotificacaoService {

    private final NotificacaoRepository repo;
    private final UtilizadorRepository utilizadorRepo;

    public NotificacaoService(NotificacaoRepository repo, UtilizadorRepository utilizadorRepo) {
        this.repo = repo;
        this.utilizadorRepo = utilizadorRepo;
    }

    public List<Notificacao> listarPorUtilizador(String utilizadorId) {
        return repo.findByUtilizadorIdOrderByDataCriacaoDesc(utilizadorId);
    }

    public List<Notificacao> notificacoesDoMes(String utilizadorId) {
        YearMonth mesAtual = YearMonth.now();
        LocalDateTime inicio = mesAtual.atDay(1).atStartOfDay();
        LocalDateTime fim = mesAtual.atEndOfMonth().atTime(23, 59, 59);

        return repo.findByUtilizadorIdAndDataCriacaoBetweenOrderByDataCriacaoDesc(utilizadorId, inicio, fim);
    }

    public Notificacao criar(String utilizadorId, Notificacao n) {
        Utilizador u = utilizadorRepo.findById(utilizadorId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        n.setUtilizador(u);
        return repo.save(n);
    }

    public Notificacao marcarComoLida(UUID id) {
        Notificacao n = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        n.setLida(true);
        return repo.save(n);
    }

    public void apagar(UUID id) {
        repo.deleteById(id);
    }
}
