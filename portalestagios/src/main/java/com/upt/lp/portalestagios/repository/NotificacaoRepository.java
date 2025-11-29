package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {

    List<Notificacao> findByUtilizadorId(UUID utilizadorId);

    List<Notificacao> findByUtilizadorIdAndLidaFalse(UUID utilizadorId);
}
