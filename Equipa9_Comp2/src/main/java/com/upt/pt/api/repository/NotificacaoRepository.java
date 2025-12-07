package com.upt.pt.api.repository;

import com.upt.pt.api.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, String> {

    // devolve notificações de um utilizador, mais recentes primeiro
    List<Notificacao> findByUtilizadorIdOrderByDataCriacaoDesc(String utilizadorId);
}
