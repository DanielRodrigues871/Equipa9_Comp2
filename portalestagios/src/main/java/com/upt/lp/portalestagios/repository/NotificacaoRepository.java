package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, String> {
}
