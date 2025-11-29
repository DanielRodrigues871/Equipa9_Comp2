package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.dto.notificacao.NotificacaoRequestDTO;
import com.upt.lp.portalestagios.dto.notificacao.NotificacaoResponseDTO;
import com.upt.lp.portalestagios.entity.Notificacao;
import com.upt.lp.portalestagios.entity.Utilizador;
import com.upt.lp.portalestagios.mapper.NotificacaoMapper;
import com.upt.lp.portalestagios.repository.NotificacaoRepository;
import com.upt.lp.portalestagios.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepo;
    private final UtilizadorRepository utilizadorRepo;

    public NotificacaoService(NotificacaoRepository notificacaoRepo, UtilizadorRepository utilizadorRepo) {
        this.notificacaoRepo = notificacaoRepo;
        this.utilizadorRepo = utilizadorRepo;
    }

    public NotificacaoResponseDTO criar(NotificacaoRequestDTO dto) {

        Utilizador u = utilizadorRepo.findById(dto.getUtilizadorId())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        Notificacao n = new Notificacao();
        n.setUtilizador(u);
        n.setMensagem(dto.getMensagem());

        notificacaoRepo.save(n);

        return NotificacaoMapper.toDTO(n);
    }

    public List<NotificacaoResponseDTO> listarPorUtilizador(UUID utilizadorId) {
        return notificacaoRepo.findByUtilizadorId(utilizadorId)
                .stream()
                .map(NotificacaoMapper::toDTO)
                .toList();
    }

    public void marcarComoLida(UUID id) {
        Notificacao n = notificacaoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));

        n.setLida(true);
        notificacaoRepo.save(n);
    }
}
