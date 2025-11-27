package com.upt.lp.portalestagios.repository;

import com.upt.lp.portalestagios.entity.OfertaEstagio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaEstagioRepository extends JpaRepository<OfertaEstagio, String> {
}

