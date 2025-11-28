package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {

    // note que o tipo do utilizadorId é String (porque Utilizador.id é String no teu modelo)
    List<Notificacao> findByUtilizadorIdOrderByDataCriacaoDesc(String utilizadorId);

    List<Notificacao> findByUtilizadorIdAndDataCriacaoBetweenOrderByDataCriacaoDesc(
            String utilizadorId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
