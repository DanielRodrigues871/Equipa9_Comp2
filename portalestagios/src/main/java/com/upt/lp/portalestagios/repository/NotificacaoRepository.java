package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Notificacao;
import com.upt.lp.portalestagios.entity.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface NotificacaoRepository extends JpaRepository<Notificacao, UUID> {

    @Query("SELECT n FROM Notificacao n WHERE n.utilizador = :user AND n.lida = false")
    List<Notificacao> findNotificacoesNaoLidas(Utilizador user);

    @Query("""
            SELECT n FROM Notificacao n 
            WHERE n.utilizador = :user 
            AND n.dataCriacao >= :inicio
            AND n.dataCriacao <= :fim
            """)
    List<Notificacao> findNotificacoesPeriodo(Utilizador user,
                                              LocalDateTime inicio,
                                              LocalDateTime fim);
    // Busca notificações de um utilizador (todas)
    List<Notificacao> findByUtilizadorId(UUID utilizadorId);

    // Busca notificações não lidas de um utilizador
    List<Notificacao> findByUtilizadorIdAndLidaFalse(UUID utilizadorId);
}
