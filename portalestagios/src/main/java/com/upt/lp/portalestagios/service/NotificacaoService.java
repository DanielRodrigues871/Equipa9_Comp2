package com.upt.lp.portalestagios.service;

import com.upt.lp.portalestagios.entity.Notificacao;
import com.upt.lp.portalestagios.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<Notificacao> findAll() {
        return notificacaoRepository.findAll();
    }

    public Optional<Notificacao> findById(String id) {
        return notificacaoRepository.findById(id);
    }

    public Notificacao save(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public void delete(String id) {
        notificacaoRepository.deleteById(id);
    }
}

