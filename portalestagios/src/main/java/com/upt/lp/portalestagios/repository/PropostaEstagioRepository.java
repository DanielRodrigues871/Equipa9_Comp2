package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.PropostaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaEstagioRepository extends JpaRepository<PropostaEstagio, String> {
}

