package com.upt.lp.componente2.repository;

import com.upt.lp.componente2.entity.PropostaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropostaEstagioRepository extends JpaRepository<PropostaEstagio, Long> {

    List<PropostaEstagio> findByEmpresaId(String empresaId);

    List<PropostaEstagio> findByRepresentanteId(String representanteId);

    List<PropostaEstagio> findByStatus(String status);

    List<PropostaEstagio> findByTipo(String tipo);
}
